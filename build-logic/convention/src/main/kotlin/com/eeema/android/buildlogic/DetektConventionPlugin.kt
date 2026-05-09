package com.eeema.android.buildlogic

import io.gitlab.arturbosch.detekt.Detekt
import io.gitlab.arturbosch.detekt.extensions.DetektExtension
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.apply
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.withType

class DetektConventionPlugin: Plugin<Project> {
    override fun apply(target: Project) {
        target.configureDetekt()
    }

    private fun Project.configureDetekt() {
        apply(plugin = "io.gitlab.arturbosch.detekt")

        configure<DetektExtension> {
            buildUponDefaultConfig = true
            allRules = false
            config.setFrom(file("$rootDir/config/detekt/detekt.yml"))
            baseline = file("$rootDir/config/detekt/baseline.xml")
        }

        tasks.withType<Detekt>().configureEach {
            reports {
                xml.required.set(true)
                html.required.set(true)
                sarif.required.set(true)
                txt.required.set(false)
            }
            exclude("**/build/**", "**/generated/**")
        }
    }
}