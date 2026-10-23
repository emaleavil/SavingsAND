pluginManagement {
    includeBuild("build-logic")
    repositories {
        google {
            content {
                includeGroupByRegex("com\\.android.*")
                includeGroupByRegex("com\\.google.*")
                includeGroupByRegex("androidx.*")
            }
        }
        mavenCentral()
        gradlePluginPortal()
    }
}
plugins {
    id("org.gradle.toolchains.foojay-resolver-convention") version "1.0.0"
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

apply(file(path = "$rootDir/gradle/scripts/gitPrePush.gradle.kts"))

rootProject.name = "Savings"

// App Module
include(":app")

include(":data")

include(":domain")

// Feature modules
include(":features:add")
include(":features:home")
include(":features:login")
include(":features:login-api")
include(":features:settings")
include(":features:home-api")
include(":features:add-api")
include(":features:settings-api")

// Utility modules
include(":ui:components")
include(":ui:theme")
