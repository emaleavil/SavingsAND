plugins {
    id("java-library")
    alias(libs.plugins.jetbrains.kotlin.jvm)
    id(
        libs.plugins.custom.spotless
            .get()
            .pluginId,
    )
    id(
        libs.plugins.custom.detekt
            .get()
            .pluginId,
    )
}
java {
    sourceCompatibility = JavaVersion.VERSION_21
    targetCompatibility = JavaVersion.VERSION_21
}
kotlin {
    compilerOptions {
        jvmTarget = org.jetbrains.kotlin.gradle.dsl.JvmTarget.JVM_21
    }
}

dependencies {
    implementation(libs.koin.core)
}
