@echo off
SET CURRENT_PATH=%~dp0
echo %CURRENT_PATH:~0,-1%

PATH %PATH%;%JAVA_HOME%\bin\
for /f tokens^=2-5^ delims^=.-_^" %%j in ('java -fullversion 2^>^&1') do set "jver=%%j"
if %jver% NEQ 11 (
    echo "Incorrect Java version. Required Java version not less than 11"
    exit 1
)

if not exist %CURRENT_PATH%\build\libs\sudoku-validator.jar (
   pushd.
   cd %CURRENT_PATH%/
   call gradlew jar
   popd
   echo Jar file was created
)

java -jar %CURRENT_PATH%\build\libs\sudoku-validator.jar %1


