SET PATH=%PATH%;"C:\Program Files\Java\jdk-16.0.1\bin"
SET PGEXE="C:\PamguardCode2021\PamGuard NOAA2\target\PamguardBeta-2.01.0601.jar"
jdeps --ignore-missing-deps -R -verbose:package %PGEXE% > pgdeps.txt