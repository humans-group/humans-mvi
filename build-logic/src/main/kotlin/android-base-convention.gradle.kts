import com.android.build.gradle.BaseExtension

@Suppress("MagicNumber")
configure<BaseExtension> {
    compileSdkVersion(33)

    defaultConfig {
        minSdk = 24
        targetSdk = 33
    }
}
