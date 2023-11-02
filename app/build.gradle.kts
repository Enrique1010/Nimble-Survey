plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.kapt)
    alias(libs.plugins.dagger.hilt)
    alias(libs.plugins.serialization.plugin)
}

android {
    namespace = "com.erapp.nimblesurvey"
    compileSdk = libs.versions.compile.sdk.version.get().toInt()

    defaultConfig {
        applicationId = "com.erapp.nimblesurvey"
        minSdk = libs.versions.min.sdk.version.get().toInt()
        targetSdk = libs.versions.target.sdk.version.get().toInt()
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        isCoreLibraryDesugaringEnabled = true
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
    }
    buildFeatures {
        compose = true
        buildConfig = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = libs.versions.compose.compiler.version.get()
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {

    // splash screen
    implementation(libs.androidx.core.splashscreen)

    // kotlin bom
    implementation(platform(libs.kotlin.bom))

    // common
    implementation(libs.androidx.core.ktlx)
    implementation(libs.serialization.json)

    // Activity
    implementation(libs.androidx.compose)

    // Lifecycle
    implementation(libs.androidx.lifecycle.runtime)
    kapt(libs.androidx.lifecycle.compiler)
    implementation(libs.androidx.view.model)
    implementation(libs.androidx.view.model.compose)
    implementation(libs.androidx.runtime.compose)
    implementation(libs.androidx.view.model.saved.state)
    implementation(libs.androidx.live.data)

    // Navigation
    implementation(libs.androidx.navigation.compose)
    androidTestImplementation(libs.androidx.navigation.test)

    // Compose
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.compose.ui)
    implementation(libs.androidx.compose.ui.util)
    implementation(libs.androidx.compose.material)
    implementation(libs.androidx.compose.tooling)
    implementation(libs.androidx.compose.foundation)
    implementation(libs.androidx.compose.livedata)
    androidTestImplementation(libs.androidx.compose.test)
    debugImplementation(libs.androidx.compose.test.manifest)
    implementation(libs.androidx.compose.extended.icons)
    implementation(libs.androidx.compose.material3)

    // Hilt
    implementation(libs.hilt.android)
    kapt(libs.hilt.android.compiler)
    kapt(libs.hilt.androidx.compiler)
    implementation(libs.hilt.navigation.compose)
    implementation(libs.androidx.hilt.common)
    implementation(libs.androidx.hilt.work)

    // Coroutines
    implementation(libs.coroutines.android)

    // Timber
    api(libs.timber)

    androidTestImplementation(platform(libs.androidx.compose.bom))
    testImplementation(libs.junit)
    androidTestImplementation(libs.junit.ext)
    androidTestImplementation(libs.espresso.core)
    testImplementation(libs.mockito.core)
}

kapt {
    correctErrorTypes = true
}