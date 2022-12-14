import com.android.build.gradle.BaseExtension

@Suppress("MagicNumber")
configure<BaseExtension> {
    compileSdkVersion(33)

    defaultConfig {
        minSdk = 21
        targetSdk = 33
    }
}
