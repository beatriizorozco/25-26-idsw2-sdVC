$ErrorActionPreference = "Stop"

$workspace = Split-Path -Parent $PSScriptRoot
$backendDirectory = Join-Path $workspace "src\backend"
$frontendDirectory = Join-Path $workspace "src\frontend"
$backendUrl = "http://127.0.0.1:8080/api/auth/csrf"
$javaHome = $env:JAVA_HOME

if ([string]::IsNullOrWhiteSpace($javaHome) -or -not (Test-Path (Join-Path $javaHome "bin\java.exe"))) {
    $javaHome = "C:\Program Files\Microsoft\jdk-17.0.12.7-hotspot"
}

if (-not (Test-Path (Join-Path $javaHome "bin\java.exe"))) {
    Write-Error "No se encontro un JDK valido. Configura JAVA_HOME con un JDK 17."
    exit 1
}

$env:JAVA_HOME = $javaHome
$env:Path = "$(Join-Path $javaHome 'bin');$env:Path"

# La extensión Java de VS Code puede dejar una caché CDS creada con otro JDK.
# Maven y Spring Boot arrancan JVM distintas, por lo que se desactiva CDS en ambas.
Remove-Item Env:JAVA_TOOL_OPTIONS, Env:JDK_JAVA_OPTIONS -ErrorAction SilentlyContinue
$env:MAVEN_OPTS = "-Xshare:off"
$env:CONSOLE_LOG_CHARSET = "UTF-8"

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
    $backendJar = Get-ChildItem (Join-Path $backendDirectory "target") -Filter "*.jar" -ErrorAction SilentlyContinue |
        Where-Object { $_.Name -notlike "*.original" } |
        Sort-Object LastWriteTime -Descending |
        Select-Object -First 1

    $latestBackendSource = Get-ChildItem (Join-Path $backendDirectory "src"), (Join-Path $backendDirectory "pom.xml") -Recurse -File |
        Sort-Object LastWriteTime -Descending |
        Select-Object -First 1

    if ($null -eq $backendJar -or $latestBackendSource.LastWriteTime -gt $backendJar.LastWriteTime) {
        Write-Host "Backend no disponible. Empaquetando Spring Boot..."

        Push-Location $backendDirectory
        try {
            & .\mvnw.cmd -DskipTests package
            if ($LASTEXITCODE -ne 0) {
                Write-Error "No se pudo empaquetar el backend."
                exit 1
            }
        }
        finally {
            Pop-Location
        }

        $backendJar = Get-ChildItem (Join-Path $backendDirectory "target") -Filter "*.jar" |
            Where-Object { $_.Name -notlike "*.original" } |
            Sort-Object LastWriteTime -Descending |
            Select-Object -First 1
    }
    else {
        Write-Host "Backend no disponible. Reutilizando el JAR existente..."
    }

    if ($null -eq $backendJar) {
        Write-Error "No se encontro el JAR ejecutable del backend."
        exit 1
    }

    Write-Host "Iniciando Spring Boot desde $($backendJar.Name)..."

    $backendProcess = Start-Process `
        -FilePath (Join-Path $javaHome "bin\java.exe") `
        -ArgumentList "-Xshare:off", "-jar", $backendJar.FullName `
        -WorkingDirectory $backendDirectory `
        -WindowStyle Hidden `
        -PassThru

    $backendReady = $false
    for ($attempt = 1; $attempt -le 90; $attempt++) {
        Start-Sleep -Seconds 1
        if ($backendProcess.HasExited) {
            Write-Host "Spring Boot no pudo iniciarse. Revisa la ventana del backend." -ForegroundColor Red
            exit 1
        }

        if (Test-Backend) {
            $backendReady = $true
            break
        }
    }

    if (-not $backendReady) {
        Write-Error "El backend no respondió en 90 segundos. Revisa la ventana del backend."
        exit 1
    }
}

Write-Host "Backend disponible en http://127.0.0.1:8080"
Write-Host "Iniciando frontend en http://127.0.0.1:5173"

Set-Location $frontendDirectory
& npm.cmd run dev:frontend
