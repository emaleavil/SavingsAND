plugins {
    id(
        libs.plugins.custom.android.libraryCompose
            .get()
            .pluginId,
    )
}

android {
    namespace = "com.eeema.android.home"
}

dependencies {
    api(project(":features:add-api"))
    api(project(":features:home-api"))
    api(project(":features:settings-api"))
    api(project(":ui:components"))
    api(project(":ui:theme"))

    implementation(libs.androidx.compose.icons.extended)
    implementation(libs.koin.androidx.compose)

    testImplementation(libs.junit)

    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(libs.androidx.junit)
}
