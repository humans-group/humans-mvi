@file:Suppress("UnstableApiUsage")

enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")

dependencyResolutionManagement {
    repositories {
        google()
        mavenCentral()
        mavenLocal()
    }
}

rootProject.name = "humans-mvi"

includeBuild("build-logic")

include(":mvi-core")
include(":mvi-sample:domain")
include(":mvi-sample:presentation")