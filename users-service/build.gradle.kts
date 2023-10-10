plugins {
    id("org.springframework.boot")
    kotlin("jvm")
    kotlin("plugin.spring")
}

val springBootVersion: String by project
val junitJupiterApiVersion: String by project

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework.boot:spring-boot-starter-security")
    testImplementation("org.junit.jupiter:junit-jupiter-api:$junitJupiterApiVersion")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:$junitJupiterApiVersion")

    implementation("org.springdoc:springdoc-openapi-ui:1.6.15")
    //runtimeOnly("org.postgresql:postgresql:42.5.4")
    implementation("org.mapstruct:mapstruct:1.5.3.Final")
    annotationProcessor("org.mapstruct:mapstruct-processor:1.5.3.Final")
    compileOnly("org.projectlombok:lombok")
    annotationProcessor("org.projectlombok:lombok")
    testImplementation("org.mockito:mockito-junit-jupiter:5.2.0")
    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testImplementation("org.springframework.security:spring-security-test")
    testAnnotationProcessor("org.mapstruct:mapstruct-processor:1.5.3.Final")
    testImplementation("org.mockito:mockito-junit-jupiter:5.2.0")
    testImplementation("org.junit.vintage:junit-vintage-engine:5.9.2")
}