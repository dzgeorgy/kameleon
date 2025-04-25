plugins {
    alias(libs.plugins.kotlin.multiplatform)
}

kotlin {
    // Targets
    jvm()

    // Config
    explicitApi()
}
