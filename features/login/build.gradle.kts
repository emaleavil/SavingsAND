plugins {
    id(
        libs.plugins.custom.android.libraryCompose
            .get()
            .pluginId,
    )
}

android {
    namespace = "com.eeema.android.login"
}

dependencies {
    api(project(":ui:components"))
    api(project(":ui:theme"))

    implementation(libs.androidx.compose.icons.extended)
    implementation(libs.androidx.compose.viewmodel) // TODO check if with androidx-lifecycle-viewmodel-navigation3 this dependency is needed

    testImplementation(libs.junit)

    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(libs.androidx.junit)
}
