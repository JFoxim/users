buildscript {
	repositories {
		mavenLocal()
		mavenCentral()
	}
}

val dependencyManagementVersion: String by project
val springBootVersion: String by project

plugins {
	id("org.springframework.boot") version "3.1.4" apply false
	id("io.spring.dependency-management") version "1.1.3" apply false
	kotlin("jvm") version "1.8.22" apply false
	kotlin("plugin.spring") version "1.8.22" apply false
	id("org.springdoc.openapi-gradle-plugin") version "1.6.0" apply false
	java
	jacoco
}

allprojects {
	group = "ru.rinat"
	version = "1.0.0"
	tasks.withType<JavaCompile> {
		sourceCompatibility = "17"
		targetCompatibility = "17"
	}
}

subprojects {
	repositories {
		mavenCentral()
	}

	apply {
		plugin("io.spring.dependency-management")
		plugin("java")
		plugin("jacoco")
	}
}

jacoco {
	toolVersion = "0.8.12"
}

tasks.test {
	useJUnitPlatform()
	finalizedBy(tasks.jacocoTestReport, tasks.jacocoTestCoverageVerification) // report is always generated after tests run
}
tasks.jacocoTestReport {
	dependsOn(tasks.test) // tests are required to run before generating the report
	reports {
		xml.required.set(true)
		csv.required.set(false)
		html.required.set(true)
	}
}

tasks.jacocoTestCoverageVerification {
	violationRules {
		rule {
			limit {
				minimum = "0.5".toBigDecimal()
			}
		}
	}
}


