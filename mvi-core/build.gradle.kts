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

    with(libs.android) {
        androidMainImplementation(x.lifecycle.livedata)
        androidMainImplementation(x.lifecycle.viewModel)
        androidMainImplementation(x.lifecycle.runtime)
        androidMainImplementation(x.fragment)
    }
}

framework {
}
