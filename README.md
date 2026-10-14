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
javac --module-path $PATH_TO_FX --add-modules javafx.controls src/main/java/com/example/Main
# Run
java --module-path $PATH_TO_FX --add-modules javafx.controls src/main/java/com/example/Main.java
```

## Typical Folder Structure

```bash
java-gradle-ui/
├── build.gradle
└── src/
    └── main/
        └── java/
            └── com/
                └── example/
                    ├── AppModule.java
                    └── Main.java

```

```bash
brew install gradle
# ** use previously installed openjdk25 instead of v27 **

# clear cache to download new dependencies
rm -rf $HOME/.gradle/caches/
gradle run

# publishes to Develocity (full report)
gradle run --scan

# Adds gradlew files
gradle wrapper
./gradlew run
```