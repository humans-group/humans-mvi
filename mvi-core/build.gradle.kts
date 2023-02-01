plugins {
    id("multiplatform-library-convention")
    id("dev.icerock.mobile.multiplatform.ios-framework")
    id("maven-publish")
}

apply("${rootProject.projectDir}/publishing/publishing-module.gradle")

dependencies {
    with(libs.mpp) {
        commonMainImplementation(kotlinx.coroutines)
        commonMainImplementation(kermit)
    }

    with(libs.android) {
        androidMainImplementation(x.lifecycle.livedata)
        androidMainImplementation(x.lifecycle.viewModel)
        androidMainImplementation(x.lifecycle.runtime)
        androidMainImplementation(x.fragment)
    }
}

framework {
}
