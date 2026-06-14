param(
    [string]$Raiz = (Resolve-Path (Join-Path $PSScriptRoot ".."))
)

$directorioDiseno = Get-ChildItem (Join-Path $Raiz "RUP") -Directory |
    Where-Object Name -Like "02-*o" |
    Select-Object -First 1
$diagramas = Join-Path $directorioDiseno.FullName "casos-uso"
$java = Join-Path $Raiz "src/backend/src/main/java"
$metodosHeredados = @(
    "count", "delete", "deleteAll", "deleteById", "existsById",
    "findAll", "findById", "flush", "save", "saveAll"
)

$metodosPorClase = @{}
Get-ChildItem $java -Recurse -Filter *.java | ForEach-Object {
    $clase = $_.BaseName
    $contenido = [IO.File]::ReadAllText($_.FullName, [Text.Encoding]::UTF8)
    $metodosExplicitos = [regex]::Matches(
        $contenido,
        '(?m)^[ \t]*(?:public|protected|private)[ \t]+(?:static[ \t]+)?(?:[\w<>\[\],.?]+[ \t]+)+(?<metodo>\w+)[ \t]*\('
    ) | ForEach-Object { $_.Groups["metodo"].Value }
    $metodosInterfaz = [regex]::Matches(
        $contenido,
        '(?m)^[ \t]*(?:[\w<>\[\],.?]+[ \t]+)+(?<metodo>\w+)[ \t]*\([^;{]*\)[ \t]*;'
    ) | ForEach-Object { $_.Groups["metodo"].Value }
    $metodosPorClase[$clase] = @((@($metodosExplicitos) + @($metodosInterfaz)) | Sort-Object -Unique)
}

$resultados = foreach ($diagrama in Get-ChildItem $diagramas -Recurse -Filter secuencia.puml) {
    $contenido = [IO.File]::ReadAllLines($diagrama.FullName, [Text.Encoding]::UTF8)
    $alias = @{}

    foreach ($linea in $contenido) {
        if ($linea -match '^\s*participant\s+"?(?<etiqueta>[^"\\]+)(?:\\n[^"]*)?"?\s+as\s+(?<alias>\w+)') {
            $alias[$matches.alias] = $matches.etiqueta.Trim()
        }
    }

    foreach ($linea in $contenido) {
        if ($linea -notmatch '^\s*\w+\s*(?:-+>|\.{2}>|-->)\s*(?<destino>\w+)\s*:\s*(?<mensaje>.+)$') {
            continue
        }

        $destino = $matches.destino
        $mensaje = $matches.mensaje
        if (-not $alias.ContainsKey($destino) -or $mensaje -notmatch '^(?<metodo>\w+)\s*\(') {
            continue
        }

        $clase = $alias[$destino]
        $metodo = $matches.metodo
        $esRespuesta = $metodo -match 'Response$'
        $esHeredado = $clase -match 'Repository$' -and $metodo -in $metodosHeredados
        $existe = $metodosPorClase.ContainsKey($clase) -and $metodo -in $metodosPorClase[$clase]

        [pscustomobject]@{
            Caso = $diagrama.Directory.Name
            Actor = $diagrama.Directory.Parent.Name
            Clase = $clase
            Metodo = $metodo
            Estado = if ($existe) {
                "Implementado"
            } elseif ($esRespuesta) {
                "Tipo de respuesta"
            } elseif ($esHeredado) {
                "Heredado de Spring Data"
            } else {
                "No declarado"
            }
            Archivo = $diagrama.FullName.Substring($Raiz.Length + 1)
        }
    }
}

$resultados |
    Sort-Object Estado, Clase, Metodo, Actor, Caso |
    Format-Table Estado, Clase, Metodo, Actor, Caso -AutoSize

$pendientes = @($resultados | Where-Object Estado -eq "No declarado")
Write-Host ""
Write-Host "Diagramas revisados: $((Get-ChildItem $diagramas -Recurse -Filter secuencia.puml).Count)"
Write-Host "Llamadas revisadas: $($resultados.Count)"
Write-Host "Llamadas a metodos no declarados: $($pendientes.Count)"

if ($pendientes.Count -gt 0) {
    exit 1
}
