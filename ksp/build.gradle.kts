plugins {
    alias(libs.plugins.kotlin.jvm)
}

dependencies {
    implementation(libs.ksp.api)
    implementation(libs.kotlinPoet.ksp)
    implementation(projects.core)
}
