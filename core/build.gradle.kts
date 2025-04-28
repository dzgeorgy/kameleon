plugins {
    alias(libs.plugins.kotlin.multiplatform)
}

kotlin {
    // Targets
    jvm()
    js()
    iosArm64()
    iosX64()
    iosSimulatorArm64()

    // Config
    explicitApi()
}
