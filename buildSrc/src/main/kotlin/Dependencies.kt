const val kotlinVersion = "1.5.20"
object BuildPlugins {
    object Versions {
        const val buildToolsVersion = "4.2.2"
        const val safeArgsPlugin = "2.3.5"
    }
    const val androidGradlePlugin = "com.android.tools.build:gradle:${Versions.buildToolsVersion}"
    const val kotlinStdPlugin = "org.jetbrains.kotlin:kotlin-gradle-plugin:$kotlinVersion"
    const val safeArgsPlugin = "androidx.navigation:navigation-safe-args-gradle-plugin:${Versions.safeArgsPlugin}"
    //Plugins
    const val androidApplication = "com.android.application"
    const val kotlinAndroid = "android"
    const val safeArgs = "androidx.navigation.safeargs.kotlin"
    const val kotlinKapt = "kotlin-kapt"
}
object AndroidSDK {
    const val compileSdkVersion = 30
    const val targetSdk = compileSdkVersion
    const val minSdk = 21
}
object Libs {
    private object Versions {
        const val coreKtxVersion = "1.6.0"
        const val appCompatVersion = "1.3.0"
        const val constraintVersion = "2.0.4"
        const val uiKtxVersion = "2.3.3"
        const val materialVersion = "1.4.0"
        const val coroutinesVersion = "1.5.0"
        const val glideVersion = "4.11.0"
        const val lifecycleVersion = "2.4.0-alpha02"
        const val retrofitVersion = "2.9.0"
        const val okHttpVersion = "4.9.1"
        const val koinVersion = "3.1.2"

    }
    const val kotlinStdLib = "org.jetbrains.kotlin:kotlin-stdlib:$kotlinVersion"
    const val coreKtx = "androidx.core:core-ktx:${Versions.coreKtxVersion}"
    const val appCompat = "androidx.appcompat:appcompat:${Versions.appCompatVersion}"
    const val constraintLayout =
        "androidx.constraintlayout:constraintlayout:${Versions.constraintVersion}"
    const val navUiKtx = "androidx.navigation:navigation-ui-ktx:${Versions.uiKtxVersion}"
    const val fragmentNavigation =
        "androidx.navigation:navigation-fragment-ktx:${Versions.uiKtxVersion}"
    const val materialDesign = "com.google.android.material:material:${Versions.materialVersion}"
    const val livedataKtx = "androidx.lifecycle:lifecycle-livedata-ktx:${Versions.lifecycleVersion}"
    const val coroutinesCore =
        "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.coroutinesVersion}"
    const val coroutinesAndroid =
        "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Versions.coroutinesVersion}"
    const val glide = "com.github.bumptech.glide:glide:${Versions.glideVersion}"
    const val glideAnnotation = "com.github.bumptech.glide:compiler:${Versions.glideVersion}"
    const val viewModelKtx =
        "androidx.lifecycle:lifecycle-viewmodel-ktx:${Versions.lifecycleVersion}"

    //Networking
    const val retrofit2 = "com.squareup.retrofit2:retrofit:${Versions.retrofitVersion}"
    const val moshiConverter = "com.squareup.retrofit2:converter-moshi:${Versions.retrofitVersion}"
    const val okHttp = "com.squareup.okhttp3:logging-interceptor:${Versions.okHttpVersion}"

    //Koin for DI
    const val koinAndroid = "io.insert-koin:koin-android:${Versions.koinVersion}"
    const val koinViewModel = "io.insert-koin:koin-android-viewmodel:${Versions.koinVersion}"

}

object TestLibs {
    private object Versions {
        const val junitVersion = "4.13.2"
        const val androidxJunitVersion = "1.1.3"
        const val espressoVersion = "3.4.0"
    }
    const val junit = "junit:junit:${Versions.junitVersion}"
    const val androidxJunit = "androidx.test.ext:junit:${Versions.androidxJunitVersion }"
    const val espresso = "androidx.test.espresso:espresso-core:${Versions.espressoVersion}"

}