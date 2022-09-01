import com.google.cloud.tools.jib.gradle.JibExtension
import org.jetbrains.kotlin.kapt3.base.Kapt.kapt
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
import org.springframework.boot.gradle.tasks.bundling.BootJar
plugins {
    kotlin("jvm") version "1.5.10"
    id("org.springframework.boot") version "2.6.7"
    id("io.spring.dependency-management") version "1.0.9.RELEASE"
    kotlin("kapt") version "1.7.10"
    kotlin("plugin.spring") version "1.5.10"
    kotlin("plugin.jpa") version "1.5.10"
    id("com.google.cloud.tools.jib") version "2.8.0" apply false
}

group = "org.example"
version = "1.0-SNAPSHOT"


buildscript {

    dependencies {
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:1.5.10")
    }
}

repositories {
    mavenCentral()
}

tasks.getByName<BootJar>("bootJar") {
    enabled = false
}

tasks {
    withType<KotlinCompile> {
        kotlinOptions {
            freeCompilerArgs = listOf("-Xjsr305=strict")
            jvmTarget = JavaVersion.VERSION_11.majorVersion
        }
    }
}

dependencies {
    apply(plugin = "com.google.cloud.tools.jib")
    apply(plugin = "kotlin")
    apply(plugin = "io.spring.dependency-management")
    apply(plugin = "org.springframework.boot")
    apply(plugin = "kotlin-spring")
    apply(plugin = "kotlin-kapt")
    apply(plugin = "kotlin-jpa")

    implementation(kotlin("stdlib"))
    implementation("io.projectreactor.kotlin:reactor-kotlin-extensions")
    implementation("org.jetbrains.kotlin:kotlin-reflect")
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-reactor")

    // spring
    implementation("org.springframework.boot:spring-boot-starter-webflux")
    implementation("org.springframework:spring-web")
    implementation("org.springframework.boot:spring-boot-autoconfigure")

    kapt("org.springframework.boot:spring-boot-configuration-processor")

    // validator
    implementation("org.springframework.boot:spring-boot-starter-validation")

    // jackson
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
    implementation("com.fasterxml.jackson.datatype:jackson-datatype-jsr310")
    implementation("com.fasterxml.jackson.datatype:jackson-datatype-hibernate5")

    // jpa
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")

    runtimeOnly("com.h2database:h2")

    developmentOnly("org.springframework.boot:spring-boot-devtools")
    runtimeOnly("mysql:mysql-connector-java")

    // test
    testImplementation("org.springframework.boot:spring-boot-starter-test") {
        exclude(group = "org.junit.vintage", module = "junit-vintage-engine")
    }
    testImplementation("com.ninja-squad:springmockk:1.1.0")
    // test
    testImplementation("io.projectreactor:reactor-test")
    testImplementation("io.mockk:mockk:1.9.3")
}

configure<JibExtension> {
    from {
        image = "openjdk:11-jdk-slim"
    }
    to {
        image = "beer1/todo-server-kotlin"
        tags = setOf("0.1.0")
    }
    container {
        mainClass = "com.tutorial.todo.TodoListApplicationKt"
        ports = listOf("9000")
        creationTime = "USE_CURRENT_TIMESTAMP"
    }
}
