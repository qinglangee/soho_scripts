rem start python virtual environment
set dir = %cd%
if "%1" == "django" (
    echo %cd%
    echo %~dp0
    cd d:\document\python_venvs
    powershell /k "cd /d d:\document\python_venvs"
    django1/Scripts/activate
    
    rem d:\document\python_venvs\django1\Scripts\activate
) else (
    echo "error param"
)

