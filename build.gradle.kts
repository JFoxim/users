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
	}
}
