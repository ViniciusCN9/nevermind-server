@echo off
for /f "delims=" %%i in ('powershell -Command "[guid]::NewGuid().ToString()"') do set "JWT_SECRET_KEY=%%i"

echo JWT_SECRET_KEY=%JWT_SECRET_KEY%

java -DJWT_SECRET_KEY=%JWT_SECRET_KEY% -jar server-0.0.1-SNAPSHOT.jar
pause
