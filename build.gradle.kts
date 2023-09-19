plugins {
	java
	id("org.springframework.boot") version "3.0.1"
	id("io.spring.dependency-management") version "1.1.0"
}

java {
	sourceCompatibility = JavaVersion.VERSION_17
	targetCompatibility = JavaVersion.VERSION_17
}
group = "com.skillbox"
version = "0.0.1-SNAPSHOT"

repositories {
	mavenCentral()
}

dependencies {
	implementation("org.springframework.boot:spring-boot-starter-data-jpa")
	runtimeOnly("com.h2database:h2")
	implementation("org.mapstruct:mapstruct:1.4.2.Final")
	annotationProcessor("org.mapstruct:mapstruct-processor:1.4.2.Final")
	implementation("org.springframework.boot:spring-boot-starter-web")
	compileOnly("org.projectlombok:lombok")
	annotationProcessor("org.projectlombok:lombok")
	testImplementation("org.springframework.boot:spring-boot-starter-test")
	testAnnotationProcessor("org.mapstruct:mapstruct-processor:1.4.2.Final")
}

tasks.withType<Test> {
	useJUnitPlatform()
}
