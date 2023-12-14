plugins {
    java
}
val springBootVersion: String by project
val junitJupiterApiVersion: String by project

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-data-jpa:$springBootVersion")
    implementation("org.postgresql:postgresql:42.6.0")
    implementation("com.zaxxer:HikariCP")
    implementation("org.liquibase:liquibase-core:4.20.0")
    implementation("org.projectlombok:lombok:1.18.26")
    implementation("org.mapstruct:mapstruct:1.5.3.Final")
    annotationProcessor("org.mapstruct:mapstruct-processor:1.5.3.Final")
    compileOnly("org.projectlombok:lombok:1.18.26")
    annotationProcessor("org.projectlombok:lombok:1.18.26")
    testImplementation("org.mockito:mockito-junit-jupiter:5.2.0")
    testImplementation("org.mockito:mockito-core")
    testImplementation("org.springframework.boot:spring-boot-starter-test:$springBootVersion")
    testImplementation("org.junit.jupiter:junit-jupiter-api:$junitJupiterApiVersion")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:$junitJupiterApiVersion")
    testImplementation("org.testcontainers:postgresql:1.18.3")
}