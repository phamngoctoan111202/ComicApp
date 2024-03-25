pluginManagement {
    repositories {
        gradlePluginPortal()
        google()
        mavenCentral()
    }
}

@Suppress("UnstableApiUsage")
dependencyResolutionManagement {
    repositories {
        google()
        // Added for testing local Konsist artifacts
        mavenLocal()
        mavenCentral()
    }
}
enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")

rootProject.name = "ComicApp"
include(":app")
include(":base")
include(":discover")
include(":genres")
include(":library")
include(":premium")
include(":account")
include(":profile")
