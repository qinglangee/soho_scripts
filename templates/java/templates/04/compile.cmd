
REM javac -d classes -cp %zh_classpath% -encoding utf-8  --module-path %PATH_TO_FX% --add-modules javafx.controls,javafx.fxml  src/*.java  client/*.java

rem set zh_classpath=classes;lib/sqlite-jdbc-3.33.0.1.jar;lib/gson-2.3.jar

call vars.cmd
@REM # Linux
@REM $ find -name "*.java" > sources.txt
@REM $ javac @sources.txt

@REM :: Windows
@REM > dir /s /B *.java > sources.txt
@REM > javac @sources.txt

@REM dir /s /B *.java > sources.txt
dir /s /B src\*.java > sources.txt

rem xcopy src\view\*.fxml classes\view\ /Y
rem xcopy src\view\*.css classes\view\ /Y

xcopy /r /s /B src\view classes\view\ /Y
javac -d classes -cp %zh_classpath% -encoding utf-8 --module-path %PATH_TO_FX% --add-modules javafx.controls,javafx.fxml,javafx.media @sources.txt
if %errorlevel%==0 (
    call run.cmd %1 %2 %3 %4 %5 %6 %7
)