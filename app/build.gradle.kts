plugins {
    id (BuildPlugins.androidApplication)
    kotlin(BuildPlugins.kotlinAndroid)
    id (BuildPlugins.safeArgs)
    id(BuildPlugins.kotlinKapt)
    id("kotlin-android")
}

android {
    compileSdkVersion(AndroidSDK.compileSdkVersion)
    buildToolsVersion("30.0.3")

    defaultConfig {
        applicationId = "io.compwiz.countrylister"
        minSdkVersion(AndroidSDK.minSdk)
        targetSdkVersion(AndroidSDK.targetSdk)
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro"
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
    buildFeatures {
        viewBinding = true
    }
}

dependencies {

    implementation(Libs.kotlinStdLib)
    implementation(Libs.coreKtx)
    implementation(Libs.appCompat)
    implementation(Libs.materialDesign)
    implementation(Libs.constraintLayout)
    // ViewModel
    implementation(Libs.viewModelKtx)
    // LiveData
    implementation(Libs.livedataKtx)
    //Navigation
    implementation(Libs.fragmentNavigation)
    implementation(Libs.navUiKtx)

    //Networking
    implementation(Libs.retrofit2)
    //Moshi
    implementation(Libs.moshiConverter)
    //OkHttp
    implementation(Libs.okHttp)

    //Koin for DI
    implementation(Libs.koinAndroid)
    //implementation(Libs.koinViewModel)

    //Coroutines
    implementation(Libs.coroutinesCore)
    implementation(Libs.coroutinesAndroid)
    //Glide
    implementation(Libs.glide)
    implementation("org.jetbrains.kotlin:kotlin-stdlib:${rootProject.extra["kotlin_version"]}")
    implementation("androidx.legacy:legacy-support-v4:1.0.0")
    //kapt(BuildPlugins.kotlinKapt)

    //Test
    testImplementation(TestLibs.junit)
    androidTestImplementation(TestLibs.androidxJunit)
    androidTestImplementation(TestLibs.espresso)
}