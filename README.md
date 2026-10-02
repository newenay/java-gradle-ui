# Java Install w Gradle

## Install Java JDK25

```bash
java -version

# Compiler
javac -version

# Get all version paths
/usr/libexec/java_home -V
```

## Typical Folder Structure

java-gradle/
├── build.gradle
└── src/
    └── main/
        └── java/
            └── com/
                └── example/
                    └── Main.java

```bash
brew install gradle
# ** use previously installed openjdk25 instead of v27 **

gradle run
```