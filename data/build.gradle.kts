plugins {
    id(
        libs.plugins.custom.android.library
            .get()
            .pluginId,
    )
}

android {
    namespace = "com.eeema.android.data"
}

dependencies {
    implementation(project(":domain"))

    implementation(libs.koin.core)

    testImplementation(libs.junit)
}
