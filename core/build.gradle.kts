plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.jetbrains.kotlin.android)
    alias(libs.plugins.hilt.plugin)
    kotlin("kapt")
}

android {
    namespace = "com.dinh.core"
    compileSdk = 34

    defaultConfig {
        minSdk = 24

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
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
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
}

dependencies {
    implementation(project(":domain"))


    val moshiVersion = "1.12.0"
    val retroifitVersion = "2.9.0"
    val okhttpClientVersion = "4.9.1"


    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)

    //retrofit
    api("com.squareup.retrofit2:retrofit:${retroifitVersion}")
    api("com.squareup.retrofit2:converter-moshi:${retroifitVersion}")

    api("com.squareup.moshi:moshi:${moshiVersion}")
    api("com.squareup.moshi:moshi-kotlin:${moshiVersion}")
    api("com.squareup.moshi:moshi-adapters:${moshiVersion}")

    //client
    api("com.squareup.okhttp3:okhttp:${okhttpClientVersion}")
    //http logging
    api("com.squareup.okhttp3:logging-interceptor:${okhttpClientVersion}")
    //hilt

    api(libs.hilt)
    kapt(libs.hiltkapt)


    // for viewmodelscope.lauch
    implementation(libs.androidx.activity.compose)


    androidTestApi("com.squareup.okhttp3:mockwebserver:${okhttpClientVersion}")

}