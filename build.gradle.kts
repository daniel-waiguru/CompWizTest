// Top-level build file where you can add configuration options common to all sub-projects/modules.
buildscript {
    repositories {
        google()
        mavenCentral()
    }
    dependencies {
        classpath(BuildPlugins.androidGradlePlugin)
        classpath(BuildPlugins.kotlinStdPlugin)
        classpath(BuildPlugins.safeArgsPlugin)
    }
}

allprojects {
    repositories {
        google()
        mavenCentral()
        maven { url = uri("https://www.jitpack.io")  }
    }
}

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}