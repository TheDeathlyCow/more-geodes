@echo off
setlocal EnableExtensions EnableDelayedExpansion
cd /d "%~dp0"
set "PORT_JAVA="
if defined JAVA_HOME call :check_jdk "%JAVA_HOME%"
if not defined PORT_JAVA for /d %%J in ("%ProgramFiles%\Java\jdk-*" "%ProgramFiles%\Eclipse Adoptium\jdk-*") do call :check_jdk "%%~fJ"
if not defined PORT_JAVA (
    echo ERROR: Install a JDK 25 or newer, then set JAVA_HOME to its folder.
    goto fail
)
set "JAVA_HOME=%PORT_JAVA%"
echo Building More Geodes for Minecraft 26.3 with:
"%JAVA_HOME%\bin\java.exe" -version
call "%~dp0gradlew.bat" --no-daemon clean build
if errorlevel 1 goto fail
if not exist "%~dp0build\libs\more-geodes-fabric-26.3-1.9.3-portV1.jar" goto fail
echo.
echo BUILD SUCCESSFUL
echo Install build\libs\more-geodes-fabric-26.3-1.9.3-portV1.jar
echo See BUILD_README.txt for Fabric API requirements and installation.
pause
exit /b 0

:check_jdk
if defined PORT_JAVA exit /b 0
if not exist "%~1\bin\javac.exe" exit /b 1
set "PORT_VERSION="
for /f "tokens=2" %%V in ('"%~1\bin\javac.exe" -version 2^>^&1') do set "PORT_VERSION=%%V"
if not defined PORT_VERSION exit /b 1
for /f "tokens=1 delims=." %%M in ("!PORT_VERSION!") do set /a PORT_MAJOR=%%M
if !PORT_MAJOR! LSS 25 exit /b 1
set "PORT_JAVA=%~1"
exit /b 0

:fail
echo BUILD FAILED. Copy the error above if you need help.
pause
exit /b 1
