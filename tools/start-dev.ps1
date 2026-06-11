$ErrorActionPreference = "Stop"

$workspace = Split-Path -Parent $PSScriptRoot
$backendDirectory = Join-Path $workspace "src\backend"
$frontendDirectory = Join-Path $workspace "src\frontend"
$backendUrl = "http://127.0.0.1:8080/api/auth/csrf"
$backendLog = Join-Path $backendDirectory "backend-run.log"
$backendErrorLog = Join-Path $backendDirectory "backend-run.err.log"
$javaHome = $env:JAVA_HOME

if ([string]::IsNullOrWhiteSpace($javaHome) -or -not (Test-Path (Join-Path $javaHome "bin\java.exe"))) {
    $javaHome = "C:\Program Files\Microsoft\jdk-17.0.12.7-hotspot"
}

if (-not (Test-Path (Join-Path $javaHome "bin\java.exe"))) {
    Write-Error "No se encontro un JDK valido. Configura JAVA_HOME con un JDK 17."
    exit 1
}

# Evita reutilizar una caché CDS incompatible cuando VS Code actualiza su JDK integrado.
$env:JAVA_HOME = $javaHome
$env:Path = "$(Join-Path $javaHome 'bin');$env:Path"
$env:JAVA_TOOL_OPTIONS = "-Xshare:off -Dstdout.encoding=UTF-8 -Dstderr.encoding=UTF-8"

function Test-BackendPort {
    $client = [System.Net.Sockets.TcpClient]::new()
    try {
        $connection = $client.BeginConnect("127.0.0.1", 8080, $null, $null)
        return $connection.AsyncWaitHandle.WaitOne(500) -and $client.Connected
    }
    finally {
        $client.Dispose()
    }
}

function Test-Backend {
    if (-not (Test-BackendPort)) {
        return $false
    }

    try {
        Invoke-WebRequest -Uri $backendUrl -UseBasicParsing -TimeoutSec 2 | Out-Null
        return $true
    }
    catch {
        return $false
    }
}

if (-not (Test-Backend)) {
    Write-Host "Backend no disponible. Iniciando Spring Boot..."

    $backendProcess = Start-Process `
        -FilePath "cmd.exe" `
        -ArgumentList "/c", ".\mvnw.cmd spring-boot:run" `
        -WorkingDirectory $backendDirectory `
        -WindowStyle Hidden `
        -RedirectStandardOutput $backendLog `
        -RedirectStandardError $backendErrorLog `
        -PassThru

    $backendReady = $false
    for ($attempt = 1; $attempt -le 90; $attempt++) {
        Start-Sleep -Seconds 1
        if ($backendProcess.HasExited) {
            Write-Host "Spring Boot no pudo iniciarse. Ultimas lineas del registro:" -ForegroundColor Red
            Get-Content $backendLog -Tail 20
            Get-Content $backendErrorLog -Tail 20
            exit 1
        }

        if (Test-Backend) {
            $backendReady = $true
            break
        }
    }

    if (-not $backendReady) {
        Write-Error "El backend no respondió en 90 segundos. Revisa src/backend/backend-run.err.log."
        exit 1
    }
}

Write-Host "Backend disponible en http://127.0.0.1:8080"
Write-Host "Iniciando frontend en http://127.0.0.1:5173"

Set-Location $frontendDirectory
& npm.cmd run dev:frontend
