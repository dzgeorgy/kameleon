plugins {
    alias(libs.plugins.kotlin.multiplatform)
}

group = "dev.dzgeorgy.kameleon"
version = "0.1.0-SNAPSHOT"

kotlin {
    jvm()

    jvmToolchain(17)

    sourceSets {
        val commonTest by getting {
            dependencies {
                implementation(libs.kotlin.test)
            }
        }
    }
}
