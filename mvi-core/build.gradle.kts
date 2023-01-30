plugins {
    id("multiplatform-library-convention")
    id("dev.icerock.mobile.multiplatform.ios-framework")
    id("com.vanniktech.maven.publish") version "0.24.0"
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

mavenPublishing {
    publishToMavenCentral(com.vanniktech.maven.publish.SonatypeHost.S01)

    coordinates(project.group.toString(), "mvi-core", project.version.toString())

    pom {
        name.set("humans-mvi")
        description.set("Simple and concise implementation of Redux/MVI approach by Humans.")
        inceptionYear.set("2023")
        url.set("https://github.com/humans-group/humans-mvi")
        licenses {
            license {
                name.set("MIT License")
                url.set("https://github.com/humans-group/humans-mvi/blob/main/LICENSE.md")
                distribution.set("https://github.com/humans-group/humans-mvi/blob/main/LICENSE.md")
            }
        }
        developers {
            developer {
                id.set("humans-group")
                name.set("Humans Group")
                url.set("mobile_dev_service@humans.net")
            }
        }
        scm {
            url.set("https://github.com/humans-group/humans-mvi")
            connection.set("scm:git:github.com/humans-group/humans-mvi.git")
            developerConnection.set("scm:git:ssh://github.com/humans-group/humans-mvi.git")
        }
    }

    signAllPublications()
}
