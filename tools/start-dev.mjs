import { spawn } from 'node:child_process'
import { existsSync, readdirSync, statSync } from 'node:fs'
import { dirname, join, resolve } from 'node:path'
import { fileURLToPath } from 'node:url'

const toolsDirectory = dirname(fileURLToPath(import.meta.url))
const workspace = resolve(toolsDirectory, '..')
const backendDirectory = join(workspace, 'src', 'backend')
const frontendDirectory = join(workspace, 'src', 'frontend')
const backendTarget = join(backendDirectory, 'target')
const backendUrl = 'http://127.0.0.1:8080/api/auth/csrf'
const commandPrompt = process.env.ComSpec ?? 'cmd.exe'
// VS Code's Java extension may set JAVA_HOME to its bundled Java 25, which is
// incompatible with this Spring Boot project's local Windows setup.
const javaHome = 'C:\\Program Files\\Microsoft\\jdk-17.0.12.7-hotspot'
const javaExecutable = join(javaHome, 'bin', 'java.exe')

let backendProcess
let frontendProcess

function cleanJavaEnvironment(extra = {}) {
  const environment = {
    ...process.env,
    JAVA_HOME: javaHome,
    CONSOLE_LOG_CHARSET: 'UTF-8',
    ...extra,
  }
  delete environment.JAVA_TOOL_OPTIONS
  delete environment.JDK_JAVA_OPTIONS
  return environment
}

function run(command, args, options = {}) {
  return spawn(command, args, {
    stdio: 'inherit',
    shell: false,
    ...options,
  })
}

function waitForExit(process) {
  return new Promise((resolveExit, reject) => {
    process.once('error', reject)
    process.once('exit', (code) => resolveExit(code ?? 1))
  })
}

async function backendIsReady() {
  try {
    const response = await fetch(backendUrl, { signal: AbortSignal.timeout(1500) })
    return response.ok
  } catch {
    return false
  }
}

async function waitForBackend(process) {
  for (let attempt = 0; attempt < 90; attempt += 1) {
    if (process.exitCode !== null) {
      throw new Error(`Spring Boot termino con codigo ${process.exitCode}.`)
    }
    if (await backendIsReady()) {
      return
    }
    await new Promise((resolveWait) => setTimeout(resolveWait, 1000))
  }
  throw new Error('El backend no respondio en 90 segundos.')
}

function findBackendJar() {
  if (!existsSync(backendTarget)) {
    return undefined
  }

  return readdirSync(backendTarget)
    .filter((name) => name.endsWith('.jar') && !name.endsWith('.jar.original'))
    .map((name) => join(backendTarget, name))
    .sort((left, right) => statSync(right).mtimeMs - statSync(left).mtimeMs)[0]
}

function stopChildren() {
  frontendProcess?.kill()
  backendProcess?.kill()
}

process.on('SIGINT', () => {
  stopChildren()
  process.exit(0)
})
process.on('SIGTERM', () => {
  stopChildren()
  process.exit(0)
})
process.on('exit', stopChildren)

try {
  if (!existsSync(javaExecutable)) {
    throw new Error(`No se encontro el JDK 17 requerido en ${javaExecutable}.`)
  }

  if (!(await backendIsReady())) {
    let backendJar = findBackendJar()
    if (!backendJar) {
      console.log('No existe el JAR. Empaquetando Spring Boot...')
      const buildProcess = run(commandPrompt, ['/d', '/s', '/c', 'mvnw.cmd', '-DskipTests', 'package'], {
        cwd: backendDirectory,
        env: cleanJavaEnvironment({ MAVEN_OPTS: '-Xshare:off' }),
      })

      if ((await waitForExit(buildProcess)) !== 0) {
        throw new Error('No se pudo empaquetar el backend.')
      }
      backendJar = findBackendJar()
    }

    if (!backendJar) {
      throw new Error('No se encontro el JAR ejecutable del backend.')
    }

    console.log(`Iniciando Spring Boot con ${javaExecutable}...`)
    backendProcess = run(javaExecutable, [
      '-DCONSOLE_LOG_CHARSET=UTF-8',
      '-Xshare:off',
      '-jar',
      backendJar,
      '--spring.profiles.active=local',
    ], {
      cwd: backendDirectory,
      env: cleanJavaEnvironment(),
    })

    await waitForBackend(backendProcess)
  }

  console.log('Backend disponible en http://127.0.0.1:8080')
  console.log('Frontend disponible en http://127.0.0.1:5173')
  frontendProcess = run(commandPrompt, ['/d', '/s', '/c', 'npm.cmd', 'run', 'dev:vite'], {
    cwd: frontendDirectory,
  })

  const frontendExitCode = await waitForExit(frontendProcess)
  stopChildren()
  process.exit(frontendExitCode)
} catch (error) {
  stopChildren()
  console.error(error instanceof Error ? error.message : error)
  process.exit(1)
}
