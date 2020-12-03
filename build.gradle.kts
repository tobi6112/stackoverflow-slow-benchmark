import kotlin.reflect.full.memberProperties

plugins {
    java
    kotlin("jvm") version "1.4.20"
    id("kotlinx.benchmark") version "0.2.0-dev-20"
    id("org.jetbrains.kotlin.plugin.allopen") version "1.4.20"
}

group = "org.example"
version = "0.1-SNAPSHOT"

repositories {
    mavenCentral()
    maven("https://dl.bintray.com/kotlin/kotlinx")
}
dependencies {
    implementation(kotlin("stdlib"))
    implementation("org.openjdk.jmh:jmh-core:1.26")
    implementation("org.jetbrains.kotlinx:kotlinx.benchmark.runtime-jvm:0.2.0-dev-20")
    implementation("org.jetbrains.kotlinx:kotlinx-cli:0.3")
}

benchmark {
    configurations {
        named("main") {
            warmups = 5
            iterations = 10
            iterationTime = 3
        }
    }
    targets {
        register("main") {
            this as kotlinx.benchmark.gradle.JvmBenchmarkTarget
            jmhVersion = "1.26"
        }
    }
}

allOpen {
    annotation("org.openjdk.jmh.annotations.State")
}