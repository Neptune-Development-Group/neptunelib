We have a [wiki](https://github.com/Neptune-Development-Group/neptunelib/wiki)

We require your java version to be version `21`

Make sure you have this added to your `build.gradle`
```java
sourceCompatibility = JavaVersion.VERSION_21
targetCompatibility = JavaVersion.VERSION_21
def targetJavaVersion = 21
tasks.withType(JavaCompile).each {
    it.options.compilerArgs.add('--enable-preview')
}
```

You also need to update your gradle to version `8.5`

Here's the project's `gradle-wrapper.properties file`

```properties
distributionBase=GRADLE_USER_HOME
distributionPath=wrapper/dists
distributionUrl=https\://services.gradle.org/distributions/gradle-8.5-bin.zip
networkTimeout=10000
zipStoreBase=GRADLE_USER_HOME
zipStorePath=wrapper/dists
```