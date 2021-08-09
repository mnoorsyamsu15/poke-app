# Poke App
### Prerequisites
- JDK (minimum version 1.8)
- Android SDK
### Install to a connected device from source
- Windows:
```
gradlew.bat installDebug
```
- *nix based OS:
```
./gradlew installDebug
```
After installing, you can launch it like so:
```
adb shell am start -n "net.mnsam.pokeapp/net.mnsam.pokeapp.splash.SplashActivity" -a android.intent.action.MAIN -c android.intent.category.LAUNCHER
```

### Run unit test
- Windows:
```
gradlew.bat testDebugUnitTest
```
- *nix based OS:
```
./gradlew testDebugUnitTest
```

### Run linter
- Windows:
```
gradlew.bat lint
```
- *nix based OS:
```
./gradlew lint
```
