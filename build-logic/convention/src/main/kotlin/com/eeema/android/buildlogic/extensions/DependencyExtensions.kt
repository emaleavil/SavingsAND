package com.eeema.android.buildlogic.extensions

import org.gradle.api.artifacts.MinimalExternalModuleDependency
import org.gradle.api.provider.Provider
import org.gradle.kotlin.dsl.DependencyHandlerScope

fun DependencyHandlerScope.implementation(dependency: Provider<MinimalExternalModuleDependency>) {
    "implementation"(dependency)
}

fun DependencyHandlerScope.testImplementation(dependency: Provider<MinimalExternalModuleDependency>) {
    "testImplementation"(dependency)
}

fun DependencyHandlerScope.androidTestImplementation(dependency: Provider<MinimalExternalModuleDependency>) {
    "androidTestImplementation"(dependency)
}

fun DependencyHandlerScope.debugImplementation(dependency: Provider<MinimalExternalModuleDependency>) {
    "debugImplementation"(dependency)
}
