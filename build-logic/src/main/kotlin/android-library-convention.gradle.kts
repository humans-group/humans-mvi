plugins {
    id("com.android.library")
    id("kotlin-android")
    id("android-base-convention")
}

android {
    sourceSets.all { java.srcDir("src/$name/kotlin") }
}

tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile>().configureEach {
    kotlinOptions.freeCompilerArgs += "-opt-in=kotlin.RequiresOptIn"
}
