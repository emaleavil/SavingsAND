plugins {
    id(
        libs.plugins.custom.android.library
            .get()
            .pluginId,
    )
}

android {
    namespace = "com.eeema.android.home"
}

dependencies {
    implementation(libs.androidx.appcompat)
    implementation(libs.androidx.core.ktx)
    implementation(libs.material)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(libs.androidx.junit)
}
