plugins {
    id("multiplatform-library-convention")
    id("dev.icerock.mobile.multiplatform.ios-framework")
    id("publish-library-convention")
}

android {
    namespace = "net.humans.kmm.mvi"
}

dependencies {
    with(libs.mpp) {
        commonMainImplementation(kotlinx.coroutines)
        commonMainImplementation(kermit)
    }

    with(libs.androidx) {
        androidMainImplementation(lifecycle.livedata)
        androidMainImplementation(lifecycle.viewModel)
        androidMainImplementation(lifecycle.runtime)
        androidMainImplementation(fragment)
    }
}

framework {
}
