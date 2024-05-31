import org.gradle.api.tasks.testing.logging.TestExceptionFormat
import org.gradle.api.tasks.testing.logging.TestLogEvent
plugins {
	application
	id("org.springframework.boot") version "3.2.7-SNAPSHOT"
	id("io.spring.dependency-management") version "1.1.5"
	id("io.sentry.jvm.gradle") version "4.6.0"
	checkstyle
	jacoco
	id("io.freefair.lombok") version "8.4"
}

group = "hexlet.code"
version = "0.0.1-SNAPSHOT"

java {
	sourceCompatibility = JavaVersion.VERSION_21
}

repositories {
	mavenCentral()
	maven { url = uri("https://repo.spring.io/snapshot") }
}

dependencies {
	implementation("org.springframework.boot:spring-boot-starter")
	testImplementation("org.springframework.boot:spring-boot-starter-test")
	testRuntimeOnly("org.junit.platform:junit-platform-launcher")
	developmentOnly("org.springframework.boot:spring-boot-devtools")
	implementation("org.springframework.boot:spring-boot-starter-data-jpa")
	implementation("org.springframework.boot:spring-boot-starter-web")
	runtimeOnly("com.h2database:h2")
	runtimeOnly("org.postgresql:postgresql:42.6.0")
	annotationProcessor("jakarta.persistence:jakarta.persistence-api:3.1.0")
	implementation("org.openapitools:jackson-databind-nullable:0.2.6")
	implementation("jakarta.validation:jakarta.validation-api:3.0.2")
	implementation("org.mapstruct:mapstruct:1.5.5.Final")
	annotationProcessor("org.mapstruct:mapstruct-processor:1.5.5.Final")
	implementation("net.datafaker:datafaker:2.0.1")
	testImplementation(platform("org.junit:junit-bom:5.10.0"))
	implementation("org.instancio:instancio-junit:3.3.0")

	testImplementation(platform("org.junit:junit-bom:5.10.0"))
	testImplementation("org.junit.jupiter:junit-jupiter:5.10.0")
	testImplementation("org.springframework.boot:spring-boot-starter-test")
	testImplementation("net.javacrumbs.json-unit:json-unit-assertj:3.2.2")
	testImplementation("org.springframework.security:spring-security-test")

	implementation("org.springframework.boot:spring-boot-starter-security")
	implementation("org.springframework.boot:spring-boot-starter-oauth2-resource-server")


}

tasks.withType<Test> {
	useJUnitPlatform()
}
checkstyle {
	toolVersion = "10.3.3"
}


tasks.jacocoTestReport {
	reports {
		xml.required = true
	}
}
buildscript {
	repositories {
		mavenCentral()
	}
}
if (System.getenv("APP_ENV") == "production") {
	sentry {
		// Generates a JVM (Java, Kotlin, etc.) source bundle and uploads your source code to Sentry.
		// This enables source context, allowing you to see your source
		// code as part of your stack traces in Sentry.
		includeSourceContext = true

		org = "home-hs5"
		projectName = "java-spring-boot"
		authToken = System.getenv("SENTRY_AUTH_TOKEN")
	}
}