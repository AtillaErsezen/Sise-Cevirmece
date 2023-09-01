pluginManagement {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
        maven{
            url =uri("https://jitpack.io")
            url=uri("https://dl.bintray.com/kotlin/kotlin-eap")
        }
    }
}

rootProject.name = "Sise Cevirmece"
include(":app")
 