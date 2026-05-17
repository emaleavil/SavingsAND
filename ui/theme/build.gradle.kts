plugins {
    id(
        libs.plugins.custom.android.libraryCompose
            .get()
            .pluginId,
    )
}

android {
    namespace = "com.eeema.android.theme"
}

dependencies {
    // TODO remove these libraries if unnecessary
    testImplementation(libs.junit)

    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(libs.androidx.junit)
}
