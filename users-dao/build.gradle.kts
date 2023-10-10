plugins {
    java
}
val springBootVersion: String by project
val junitJupiterApiVersion: String by project

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-data-jpa:$springBootVersion")
    runtimeOnly("org.postgresql:postgresql:42.5.4")
    implementation("org.mapstruct:mapstruct:1.5.3.Final")
    annotationProcessor("org.mapstruct:mapstruct-processor:1.5.3.Final")
    compileOnly("org.projectlombok:lombok")
    annotationProcessor("org.projectlombok:lombok")
    testImplementation("org.mockito:mockito-junit-jupiter:5.2.0")
    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testImplementation("org.junit.jupiter:junit-jupiter-api:$junitJupiterApiVersion")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:$junitJupiterApiVersion")
}