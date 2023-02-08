plugins {
    id("multiplatform-library-convention")
    id("dev.icerock.mobile.multiplatform.ios-framework")
}

android {
    namespace = "net.humans.kmm.mvi"
}

ext["artifactId"] = "mvi-core"

apply(plugin = "publish-library-convention")

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
