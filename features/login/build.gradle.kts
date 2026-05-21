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
    api(project(":features:login-api"))
    api(project(":ui:components"))

    implementation(libs.androidx.compose.icons.extended)
    implementation(libs.androidx.compose.viewmodel) // TODO check if with androidx-lifecycle-viewmodel-navigation3 this dependency is needed

    testImplementation(libs.junit)

    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(libs.androidx.junit)
}
