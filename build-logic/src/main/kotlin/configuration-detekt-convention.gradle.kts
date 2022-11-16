plugins {
    id("io.gitlab.arturbosch.detekt")
}

val detektVersion: String = "1.21.0"

tasks.getByName("detekt").enabled = false

tasks.register<io.gitlab.arturbosch.detekt.Detekt>("detektAll") {
    version = detektVersion
    parallel = true
    ignoreFailures = false
    setSource(files(projectDir))
    exclude("**/build/**")

    buildUponDefaultConfig = false
    reports {
        xml.required.set(false)
        txt.required.set(false)
        html {
            required.set(true)
            outputLocation.set(File(project.buildDir, "reports/detekt.html"))
        }
    }
}
