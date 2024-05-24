plugins {
	application
	id("org.springframework.boot") version "3.2.7-SNAPSHOT"
	id("io.spring.dependency-management") version "1.1.5"
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
	implementation("org.springframework.boot:spring-boot-starter-security")
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
