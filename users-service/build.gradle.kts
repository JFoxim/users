plugins {
    id("org.springframework.boot")
    kotlin("jvm")
    kotlin("plugin.spring")
    id("org.springdoc.openapi-gradle-plugin") version "1.6.0"
}

val lombokMapstructBindingVersion: String by project
val junitJupiterApiVersion: String by project
val lombokVersion: String by project
val mapstructVersion: String by project
val springdocOpenapiUiVersion: String by project
val mockitoJunitJupiterVersion: String by project
val springdocOpenapiUiWebMvcVersion: String by project
val assertjCoreVersion: String by project

dependencies {
    implementation(project(":users-usecase"))
    implementation(project(":users-dao"))
    implementation(project(":users-util"))

    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework.boot:spring-boot-starter-validation")
    implementation("com.fasterxml.jackson.datatype:jackson-datatype-jsr310:2.14.0")
    //implementation("org.springdoc:springdoc-openapi-ui:$springdocOpenapiUiVersion") 1.6.15
    // uses for spring boot 3 version
    implementation("org.springdoc:springdoc-openapi-starter-webmvc-ui:$springdocOpenapiUiVersion")

    //implementation("org.springframework.boot:spring-boot-starter-security")
    testImplementation("org.junit.jupiter:junit-jupiter-api:$junitJupiterApiVersion")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:$junitJupiterApiVersion")
    testImplementation("org.assertj:assertj-core:$assertjCoreVersion")


    implementation("org.projectlombok:lombok:$lombokVersion")
    testImplementation("org.projectlombok:lombok:$lombokVersion")
    implementation("org.mapstruct:mapstruct:$mapstructVersion")
    annotationProcessor("org.projectlombok:lombok-mapstruct-binding:$lombokMapstructBindingVersion")
    annotationProcessor("org.mapstruct:mapstruct-processor:$mapstructVersion")
    annotationProcessor("org.projectlombok:lombok:$lombokVersion")

    testImplementation("org.mockito:mockito-junit-jupiter:$mockitoJunitJupiterVersion")
    testImplementation("org.springframework.boot:spring-boot-starter-test")
    //testImplementation("org.springframework.security:spring-security-test")
    testImplementation("org.junit.vintage:junit-vintage-engine:5.9.2")
}

tasks.test {
    useJUnitPlatform()
}
