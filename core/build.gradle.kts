plugins {
    alias(libs.plugins.kotlin.multiplatform)
}

kotlin {
    // Targets
    jvm()
    js()

    // Config
    explicitApi()
}
