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