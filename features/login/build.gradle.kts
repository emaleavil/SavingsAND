plugins {
    id(
        libs.plugins.custom.android.libraryCompose
            .get()
            .pluginId,
    )
}

android {
    namespace = "com.eeema.android.login"

    buildFeatures {
        compose = true
    }
}

dependencies {
    api(project(":ui:theme"))

    testImplementation(libs.junit)

    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(libs.androidx.junit)
}
