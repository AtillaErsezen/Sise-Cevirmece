pluginManagement {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositories {
        google()
        mavenCentral()
        maven{
            url =uri("https://jitpack.io")
        }
        maven{
            url=uri("https://dl.bintray.com/kotlin/kotlin-eap")
        }
    }
}

rootProject.name = "Sise Cevirmece"
include(":app")
 