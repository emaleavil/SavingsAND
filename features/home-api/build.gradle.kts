plugins {
    id(
        libs.plugins.custom.android.library
            .get()
            .pluginId,
    )
    alias(libs.plugins.jetbrains.kotlin.serialization)
}

android {
    namespace = "com.eeema.android.home.api"
}

dependencies {
    implementation(libs.androidx.navigation3.runtime)
}
