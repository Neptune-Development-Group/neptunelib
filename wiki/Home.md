# Features

## [Registration](%wiki%/registration)
Neptunelib has an extensive system that cuts down on a lot of the boilerplate when it comes to registering `Items`, `Blocks`, `Ores`, `Sounds`

This also hooks into our custom data system for easily adding component data to `Items` without all the boilerplate

## [Item Groups](%wiki%/item_groups)
We have a custom system for custom Item Groups that allow you to easily add an Item Groups to your `Items` and `BlockItems`, without needing to hook into an Event to hook it up yourself

## [Easy Data](%wiki%/easy_data)
On top of the rest of the feature set of neptunelib, we also include a system to easily add new data to `Items`

## [Datagen](%wiki%/datagen)
Neptunelib has it's own built of extension of Fabric's datagen system that handles a lot of the brunt work for you, and just lets you add features without the extra boilerplate

## [Misc](%wiki%/misc)
Miscellaneous features that are too small or independent to have their own page, but could prove of some use for some people

## Setup

````groovy
repositories {
    maven {
            name = "Jitpack"
            url = uri("https://jitpack.io")
            metadataSources {
                artifact() // Look directly for artifact
            }
        }
}

dependencies {
    modImplementation "com.neptunedevelopmentteam:neptunelib:${project.neptunelib_version}"
    include "com.neptunedevelopmentteam:neptunelib:${project.neptunelib_version}"
}
````

Replace `${project.neptunelib_version}` with the version you want or define it in your `gradle.properties` file


Here's an example of a `gradle.properties` file

```properties
# Done to increase the memory available to gradle.
org.gradle.jvmargs=-Xmx1G

# Fabric Properties
# check these on https://modmuss50.me/fabric.html
minecraft_version=1.21
yarn_mappings=1.21+build.2
loader_version=0.15.11

# Mod Properties
mod_version = 1.0
maven_group = com.neptunedevelopmentteam
archives_base_name = nukasonic

# Dependencies
# check this on https://modmuss50.me/fabric.html
fabric_version=0.100.1+1.21
neptunelib_version = 2.0.2+1.21
```

You can always set the version of `neptunelib` to the latest version from [here](https://github.com/Neptune-Development-Group/neptunelib/releases)