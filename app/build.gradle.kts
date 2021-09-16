/*
 * MIT License
 *
 * Copyright (c) 2021 Daniel Waiguru
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 *
 */

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
        testInstrumentationRunner = "io.compwiz.runner.MockTestRunner"
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
    androidTestImplementation(TestLibs.kakao)
    androidTestImplementation(TestLibs.runner)
    androidTestImplementation(TestLibs.expressoContrib)


    debugImplementation(TestLibs.truth)
    debugImplementation(TestLibs.androidxArchCore)
    debugImplementation(TestLibs.coroutineTest)
    debugImplementation(TestLibs.fragmentTesting)
}