plugins {
    id("android-app-convention")
}

android {
    namespace = "net.humans.kmm.mvi.sample"
    defaultConfig {
        applicationId = "net.humans.kmm.mvi.sample"
    }
}

dependencies {
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime)
    implementation(libs.androidx.lifecycle.viewmodel.compose)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.compose.ui)
    implementation(libs.androidx.compose.ui.graphics)
    implementation(libs.androidx.compose.ui.tooling.preview)
    implementation(libs.androidx.compose.material3)
    implementation(libs.com.ionspin.kotlin.bignum)
    implementation(project(":mvi-core"))
    implementation(project(":mvi-sample:domain"))

    debugImplementation(libs.androidx.compose.ui.tooling)
    debugImplementation(libs.androidx.compose.ui.test.manifest)
}