import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    `kotlin-dsl`
}

group = "com.eeema.android.buildlogic"

java {
    sourceCompatibility = JavaVersion.VERSION_21
    targetCompatibility = JavaVersion.VERSION_21
}

kotlin {
    compilerOptions {
        jvmTarget = JvmTarget.JVM_21
    }
}

tasks {
    validatePlugins {
        enableStricterValidation = true
        failOnWarning = true
    }
}


dependencies {
    compileOnly(libs.android.gradlePlugin)
    compileOnly(libs.kotlin.gradlePlugin)
    compileOnly(libs.spotless.gradlePlugin)
    compileOnly(libs.detekt.gradlePlugin)
}

gradlePlugin {
    plugins {
        register("spotless") {
            id = libs.plugins.custom.spotless.get().pluginId
            implementationClass = "com.eeema.android.buildlogic.SpotlessConventionPlugin"
        }
        register("detekt") {
            id = libs.plugins.custom.detekt.get().pluginId
            implementationClass = "com.eeema.android.buildlogic.DetektConventionPlugin"
        }
        register("androidLibrary") {
            id = libs.plugins.custom.android.library.get().pluginId
            implementationClass = "com.eeema.android.buildlogic.AndroidLibraryConventionPlugin"
        }
    }
}