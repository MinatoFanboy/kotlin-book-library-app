# Shopping UI

![Kotlin](https://img.shields.io/badge/kotlin-2.0.0-blue) [![Java Version](https://img.shields.io/badge/java-17-blue)](https://www.oracle.com/java/technologies/downloads/)

## Setup

### Terminal Commands

1. Add wrapper

   ```bash
   gradle wrapper
   ```

2. Connect WSA

   ```bash
   netsh.exe interface ip show address | adb connect <ip>
   ```

3. Update dependencies

   ```bash
   ./gradlew --refresh-dependencies
   ```

4. Change java version

   ````bash
   sudo update-alternatives --config java
   ```

   ````

5. Debug app

   ```bash
   ./gradlew assembleDebug && adb install app/build/outputs/apk/debug/app-debug.apk
   ```
