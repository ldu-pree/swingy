# Swingy

## Running Swingy:
### Please run this command to enable colour in you win10 command line:
reg add HKEY_CURRENT_USER\Console /v VirtualTerminalLevel /t REG_DWORD /d 0x00000001 /f
### If the above line gives you an error run this:
reg add HKEY_CURRENT_USER\Console /v VirtualTerminalLevel /t REG_DWORD /d 0x00000000 /f

## Now Swingy
#### To Build It:
mvn clean package
#### Or:
Click on the build.bat file
#### To Run It (Requires you to build first):
java -jar target/swingy-1.jar console/gui
#### Or:
Click on gui.bat/console.bat depending on which you'd like to launch