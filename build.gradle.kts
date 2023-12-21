plugins {
    kotlin("jvm") version "1.9.0"
    id("org.flywaydb.flyway") version "10.0.0"

    application
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(kotlin("test"))
    implementation("org.postgresql:postgresql:42.2.27")
}

tasks.test {
    useJUnitPlatform()
}

kotlin {
    jvmToolchain(8)
}

application {
    mainClass.set("MainKt")
}

configure<org.flywaydb.gradle.FlywayExtension> {
    url = System.getenv("DB_URL") ?: "fallback-db-url"
    user = System.getenv("DB_USER") ?: "fallback-db-user"
    password = System.getenv("DB_PASSWORD") ?: "fallback-db-password"
}
