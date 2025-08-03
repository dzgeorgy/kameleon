plugins {
    kotlin("jvm") version "2.2.0"
}

group = "dev.dzgeorgy.kameleon"
version = "unspecified"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(kotlin("test"))
}

tasks.test {
    useJUnitPlatform()
}
kotlin {
    jvmToolchain(8)
}