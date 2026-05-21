plugins {
    id(
        libs.plugins.custom.android.libraryCompose
            .get()
            .pluginId,
    )
}

android {
    namespace = "com.eeema.android.components"
}

dependencies {
    api(project(":ui:theme"))
    api(libs.androidx.navigation3.runtime)
    api(libs.androidx.navigation3.ui)

    // TODO remove these libraries if unnecessary
    testImplementation(libs.junit)

    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(libs.androidx.junit)
}
