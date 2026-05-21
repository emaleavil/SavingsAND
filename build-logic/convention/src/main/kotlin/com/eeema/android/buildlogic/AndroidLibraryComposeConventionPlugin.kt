package com.eeema.android.buildlogic

import com.android.build.api.dsl.LibraryExtension
import com.eeema.android.buildlogic.extensions.androidTestImplementation
import com.eeema.android.buildlogic.extensions.debugImplementation
import com.eeema.android.buildlogic.extensions.implementation
import com.eeema.android.buildlogic.extensions.libs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.apply
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.dependencies

class AndroidLibraryComposeConventionPlugin: Plugin<Project> {

    override fun apply(target: Project) {
        target.configure()
    }

    private fun Project.configure() {
        apply(plugin = "com.eeema.android.library")
        apply(plugin = "org.jetbrains.kotlin.plugin.compose")
        configureCompose()
    }

    private fun Project.configureCompose() {
        extensions.configure<LibraryExtension> {
            buildFeatures.compose = true
        }
        dependencies {
            val composeBom = libs.findLibrary("androidx-compose-bom").get()

            implementation(platform(composeBom))
            implementation(libs.findLibrary("androidx-compose-material3").get())
            implementation(libs.findLibrary("androidx-compose-ui").get())
            implementation(libs.findLibrary("androidx-compose-ui-tooling-preview").get())

            androidTestImplementation(platform(composeBom))
            androidTestImplementation(libs.findLibrary("androidx-compose-ui-test-junit4").get())

            debugImplementation(libs.findLibrary("androidx-compose-ui-test-manifest").get())
            debugImplementation(libs.findLibrary("androidx-compose-ui-tooling").get())
        }
    }
}
