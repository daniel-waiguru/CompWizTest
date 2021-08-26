plugins {
    id (BuildPlugins.androidApplication)
    kotlin(BuildPlugins.kotlinAndroid)
    id (BuildPlugins.safeArgs)
    id(BuildPlugins.kotlinKapt)
    id(BuildPlugins.kotlinParcelize)
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

        vectorDrawables.useSupportLibrary = true
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
    testOptions {
        animationsDisabled = true
        unitTests {
            isIncludeAndroidResources = true
            isReturnDefaultValues = true
        }
    }
    packagingOptions {
        exclude("META-INF/DEPENDENCIES")
        exclude("META-INF/AL2.0")
        exclude("META-INF/LGPL2.1")
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

    //Rv
    implementation(Libs.rvSelection)
    //Glide
    implementation(Libs.glide)
    implementation(Libs.supportV4)
    kapt(Libs.glideAnnotation)
    implementation(Libs.glideVector)

    //Test
    testImplementation(TestLibs.junit)
    testImplementation(TestLibs.mockWebServer)
    testImplementation(TestLibs.mockito)
    testImplementation(TestLibs.robolectric)
    testImplementation(TestLibs.koinTest)
    testImplementation(TestLibs.mockk)
    testImplementation(TestLibs.runner)
    testImplementation(TestLibs.liveDataTest)

    androidTestImplementation(TestLibs.mockWebServer)
    androidTestImplementation(TestLibs.androidxJunit)
    androidTestImplementation(TestLibs.espresso)
    androidTestImplementation(TestLibs.koinTest)
    androidTestImplementation(TestLibs.mockInstrumented)
    androidTestImplementation(TestLibs.rules)

    debugImplementation(TestLibs.truth)
    debugImplementation(TestLibs.androidxArchCore)
    debugImplementation(TestLibs.coroutineTest)
    debugImplementation(TestLibs.fragmentTesting)
}