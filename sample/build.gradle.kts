plugins {
    alias(libs.plugins.kotlin.multiplatform)
    alias(libs.plugins.ksp)
}

kotlin {
    jvm()
    js()

    sourceSets {
        commonMain.dependencies {
            implementation(projects.core)
        }
    }
}

dependencies {
    add("kspCommonMainMetadata", projects.ksp)
}
