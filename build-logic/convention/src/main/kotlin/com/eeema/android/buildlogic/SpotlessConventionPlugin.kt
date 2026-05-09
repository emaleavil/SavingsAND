package com.eeema.android.buildlogic

import com.diffplug.gradle.spotless.SpotlessExtension
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.internal.Actions.with
import org.gradle.kotlin.dsl.apply
import org.gradle.kotlin.dsl.configure

class SpotlessConventionPlugin: Plugin<Project> {

    override fun apply(target: Project) {
        with(target) {
            apply(plugin = "com.diffplug.spotless")
            configure<SpotlessExtension> {
                kotlin {
                    target("**/*.kt")
                    targetExclude("**/build/**", "**/generated/**")
                    ktlint()
                        .setEditorConfigPath("$rootDir/.editorconfig")
                        .editorConfigOverride(
                            mapOf(
                                "ktlint_function_naming_ignore_when_annotated_with" to "Composable",
                                "ktlint_standard_no-wildcard-imports" to "disabled"
                            )
                        )
                    trimTrailingWhitespace()
                    endWithNewline()

                }

                kotlinGradle {
                    target("**/*.kts")
                    ktlint()
                    trimTrailingWhitespace()
                    endWithNewline()
                }

                format("xml") {
                    target("**/*.xml")
                    targetExclude("**/build/**", "**/generated/**")
                    trimTrailingWhitespace()
                    endWithNewline()
                }

                format("misc") {
                    target("**/*.md", "**/*.gitignore", "**/*.yml", "**/*.yaml")
                    trimTrailingWhitespace()
                    endWithNewline()
                }
            }
        }
    }
}