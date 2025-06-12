val jacksonVersion: String = "2.19.0"
val mockkVersion: String = "1.14.2"
val detektVersion: String = "1.23.5"

plugins {
    kotlin("jvm") version "2.1.20"
    id("com.github.johnrengelman.shadow") version "8.1.1"
    id("io.gitlab.arturbosch.detekt") version "1.23.5"
}

group = "com.capitalgainscli"
version = "1.0"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(kotlin("test"))

    implementation("com.fasterxml.jackson.module:jackson-module-kotlin:$jacksonVersion")

    testImplementation("io.mockk:mockk:$mockkVersion")

    detekt("io.gitlab.arturbosch.detekt:detekt-formatting:$detektVersion")
    detekt("io.gitlab.arturbosch.detekt:detekt-cli:$detektVersion")
}

tasks.test {
    useJUnitPlatform()
}

kotlin {
    jvmToolchain(21)
}

tasks.named<com.github.jengelman.gradle.plugins.shadow.tasks.ShadowJar>("shadowJar") {
    archiveBaseName.set("capitalgainscli")
    archiveClassifier.set("")
    archiveVersion.set("1.0")

    manifest {
        attributes["Main-Class"] = "com.capitalgainscli.MainKt"
    }
}

tasks {
    build {
        dependsOn(shadowJar)
    }

    jar {
        enabled = false
    }
}

detekt {
    toolVersion = detektVersion
    autoCorrect = true
}