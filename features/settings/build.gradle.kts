plugins {
    id(
        libs.plugins.custom.android.libraryCompose
            .get()
            .pluginId,
    )
}

android {
    namespace = "com.eeema.android.settings"
}

dependencies {
    api(project(":features:settings-api"))
    api(project(":ui:components"))
    api(project(":ui:theme"))

    testImplementation(libs.junit)
    implementation(libs.koin.androidx.compose)

    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(libs.androidx.junit)
}
