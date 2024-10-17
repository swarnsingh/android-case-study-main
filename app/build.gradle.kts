plugins {
  alias(libs.plugins.android.application)
  alias(libs.plugins.jetbrains.kotlin.android)
  alias(libs.plugins.compose.compiler)
  alias(libs.plugins.kotlin.ksp)
  alias(libs.plugins.hilt.library)
}

android {
  namespace = "com.target.targetcasestudy"
  compileSdk = 35

  defaultConfig {
    applicationId = "com.target.targetcasestudy"
    minSdk = 24
    targetSdk = 35
    versionCode = 1
    versionName = "1.0"
    multiDexEnabled = true
    testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
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
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
  }
  kotlinOptions {
    jvmTarget = "17"
  }
}

dependencies {
  implementation(libs.androidx.core.ktx)
  implementation(libs.androidx.appcompat)
  implementation(libs.androidx.constraintlayout)
  implementation(libs.androidx.recyclerview)
  implementation(libs.androidx.fragment.ktx)

  // Network
  implementation(libs.retrofit)
  implementation(libs.retrofit.converter.moshi)
  implementation(libs.okhttp)
  implementation(libs.okhttp.logging.interceptor)

  // JSON Parsing
  implementation(libs.moshi)
  implementation(libs.moshi.kotlin)
  ksp(libs.moshi.kotlin.codegen)

  implementation(libs.androidx.activity.compose)
  implementation(platform(libs.androidx.compose.bom))
  implementation(libs.androidx.navigation.compose)
  implementation(libs.androidx.lifecycle.runtime.compose)

  // Image Loading
  implementation(libs.coil.lib)
  implementation(libs.coil.compose)

  implementation(libs.androidx.ui)
  implementation(libs.androidx.ui.graphics)
  implementation(libs.androidx.material3)

  implementation(libs.kotlinx.coroutines.android)
  implementation(libs.kotlinx.coroutines.core)

  implementation(libs.androidx.lifecycle.runtime.ktx)

  implementation(libs.androidx.hilt.navigation.compose)
  implementation(libs.hilt.android)
  ksp(libs.hilt.android.compiler)

  testImplementation(libs.junit)
  androidTestImplementation(libs.androidx.junit)
  androidTestImplementation(libs.androidx.espresso.core)
  androidTestImplementation(libs.androidx.ui.test.junit4)

  debugImplementation(libs.androidx.compose.ui.tooling)
  debugImplementation(libs.androidx.compose.ui.tooling.preview)
  androidTestImplementation(platform(libs.androidx.compose.bom))
}
