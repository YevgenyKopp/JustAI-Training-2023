
val jaicf = "1.2.4"
val logback = "1.2.11"
val ktor = "1.5.1"
val springBoot = "2.7.4"

plugins {
    application
    kotlin("jvm") version "1.6.21"
    kotlin("plugin.spring") version "1.6.21"
    id("org.springframework.boot") version "2.7.4"
    id("io.spring.dependency-management") version "1.0.14.RELEASE"
    id("org.datlowe.maven-publish-auth") version "2.0.2"
}

group = "org.justai.training"
version = "0.0.1"

repositories {
    mavenLocal()
    mavenCentral()
    maven(uri("https://jitpack.io"))
    maven("https://plugins.gradle.org/m2")
}

dependencies {
    implementation("com.just-ai.jaicf:core:$jaicf")
    implementation("com.just-ai.jaicf:jaicp:$jaicf")
    implementation("com.just-ai.jaicf:caila:$jaicf")

    implementation(files("/home/kopp/IdeaProjects/jaisl/build/libs/jaisl-2.2.0-SNAPSHOT-plain.jar"))


    implementation("org.springframework.boot:spring-boot:$springBoot")
    implementation("org.springframework.boot:spring-boot-starter-web:$springBoot")
    implementation("org.springframework.boot:spring-boot-starter-tomcat:$springBoot")
    implementation("org.springframework.boot:spring-boot-starter:$springBoot")
    implementation("org.springframework.boot:spring-boot-starter-data-mongodb:$springBoot")
    implementation("org.springframework.boot:spring-boot-configuration-processor:$springBoot")

    implementation("io.ktor:ktor-client-core:$ktor")
    implementation("io.ktor:ktor-client-cio:$ktor")
    implementation("io.ktor:ktor-client-logging:$ktor")
    implementation("io.ktor:ktor-client-features:$ktor")
    implementation("io.ktor:ktor-client-serialization:$ktor")
    implementation("io.ktor:ktor-client-gson:$ktor")

    implementation ("com.google.code.gson:gson:2.8.9")

    implementation("org.datlowe.maven-publish-auth:org.datlowe.maven-publish-auth.gradle.plugin:2.0.2")

    testImplementation("org.springframework.boot:spring-boot-starter-test")

    api("com.amazonaws:aws-java-sdk-s3:1.12.322")
}

tasks.withType<Test> {
    useJUnitPlatform()
}

application {
    project.setProperty("mainClassName", "com.justai.training.ApplicationKt")
}

tasks.withType(org.springframework.boot.gradle.tasks.bundling.BootJar::class.java).configureEach {
    duplicatesStrategy = DuplicatesStrategy.EXCLUDE
}

tasks.named<org.springframework.boot.gradle.tasks.bundling.BootJar>("bootJar") {
    archiveFileName.set("app-all.jar")
}