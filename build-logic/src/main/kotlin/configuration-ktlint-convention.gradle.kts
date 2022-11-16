plugins {
    id("org.jlleitschuh.gradle.ktlint")
}

configure<org.jlleitschuh.gradle.ktlint.KtlintExtension> {
    version.set("0.43.2")
    disabledRules.set(setOf("import-ordering"))
}
