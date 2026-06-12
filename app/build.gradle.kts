plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.compose)
    id(
        libs.plugins.custom.spotless
            .get()
            .pluginId,
    )
    id(
        libs.plugins.custom.detekt
            .get()
            .pluginId,
    )
    id(
        libs.plugins.custom.android.lint
            .get()
            .pluginId,
    )
}

android {
    namespace = "com.eeema.android.savings"
    compileSdk {
        version =
            release(36) {
                minorApiLevel = 1
            }
    }

    defaultConfig {
        applicationId = "com.eeema.android.savings"
        minSdk = 24
        targetSdk = 36
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro",
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_21
        targetCompatibility = JavaVersion.VERSION_21
    }
    buildFeatures {
        compose = true
    }

    lint {
        abortOnError = true
        warningsAsErrors = false

        htmlReport = true
        xmlReport = true
        sarifReport = true

        checkGeneratedSources = false
        checkDependencies = false
    }
}

dependencies {

    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.activity.compose)
    implementation(libs.androidx.compose.material3)
    implementation(libs.androidx.compose.ui)
    implementation(libs.androidx.compose.ui.graphics)
    implementation(libs.androidx.compose.ui.tooling.preview)
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)

    // Feature modules
    implementation(project(":features:add"))
    implementation(project(":features:home"))
    implementation(project(":features:login"))
    implementation(project(":features:settings"))
    implementation(project(":features:transactions"))

    testImplementation(libs.junit)

    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.compose.ui.test.junit4)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(libs.androidx.junit)

    debugImplementation(libs.androidx.compose.ui.test.manifest)
    debugImplementation(libs.androidx.compose.ui.tooling)
}
