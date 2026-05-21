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

// Feature modules
include(":features:add")
include(":features:home")
include(":features:login")
include(":features:login-api")
include(":features:settings")
include(":features:transactions")
include(":ui:components")
include(":ui:theme")
include(":features:home-api")
