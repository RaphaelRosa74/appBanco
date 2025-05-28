plugins {
    kotlin("jvm") version "1.9.0"
    application
    id("org.jetbrains.kotlin.plugin.serialization") version "1.9.0"
}

group = "com.example"
version = "1.0.0"

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.xerial:sqlite-jdbc:3.41.2.1")
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.5.1")
    
    testImplementation(kotlin("test"))
    testImplementation("org.junit.jupiter:junit-jupiter:5.9.2")
}

tasks.test {
    useJUnitPlatform()
}

application {
    mainClass.set("MainKt")
}