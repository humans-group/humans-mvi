repositories {
    gradlePluginPortal()
    google()
    mavenCentral()
}

plugins {
    `kotlin-dsl`
}

dependencies {
    val kotlinVersion = "1.7.21"
    val agpVersion = "7.3.1"
    val detektVersion = "1.21.0"
    val ktlintVersion = "10.2.1"
    val mobileMultiplatformVersion = "0.12.0"
    implementation("dev.icerock:mobile-multiplatform:$mobileMultiplatformVersion")
    implementation("org.jetbrains.kotlin:kotlin-gradle-plugin:$kotlinVersion")
    implementation("com.android.tools.build:gradle:$agpVersion")
    implementation("io.gitlab.arturbosch.detekt:detekt-gradle-plugin:$detektVersion")
    implementation("org.jlleitschuh.gradle:ktlint-gradle:$ktlintVersion")
}

project.tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {
    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_11.toString()
    }
}
project.tasks.withType<JavaCompile> {
    sourceCompatibility = JavaVersion.VERSION_11.toString()
    targetCompatibility = JavaVersion.VERSION_11.toString()
}
