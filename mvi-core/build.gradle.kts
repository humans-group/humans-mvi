plugins {
    id("multiplatform-library-convention")
    id("dev.icerock.mobile.multiplatform.ios-framework")
    id("maven-publish")
}

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
