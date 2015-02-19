DIM objShell 
set objShell=wscript.createObject("wscript.shell") 
iReturn=objShell.Run("javaw -jar Layout2Style.jar", 0, TRUE)