# Java Install w Gradle

## Install Java JDK25

```bash
brew install --cask temurin@25
java -version

# Compiler
javac -version

# Get all version paths
/usr/libexec/java_home -V

# $PATH_TO_FX: '/Library/Java/JavaFx/javafx-sdk-25.0.4/lib'
# Creates a Main.class File
javac --module-path $PATH_TO_FX --add-modules javafx.controls src/main/java/com/example/Main.java

### Guice Dependency memory override
java --sun-misc-unsafe-memory-access=allow

# Run
java --module-path $PATH_TO_FX --add-modules javafx.controls src/main/java/com/example/Main.java 
```
This project is a boiler plate JavaFX WebUI combined with a Guice Messenger.  

### TODO:
1. Continue to refine and reduce the boilerplate down to its simplest level
1. Then implement a database

## Run the project
```bash
brew install gradle
# ** use previously installed openjdk25 instead of v27 **

# clear cache to download new dependencies
rm -rf $HOME/.gradle/caches/
gradle clean build --warning-mode all

# publishes to Develocity (full report)
gradle run --scan

# Adds gradlew files
gradle wrapper
# Verify connections to JVM
./gradlew --version
./gradlew javaToolChains

./gradlew run
# Ctrl + c 
```