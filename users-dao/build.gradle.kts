
plugins {
    java
}

val springBootVersion: String by project
val junitJupiterApiVersion: String by project
val lombokMapstructBindingVersion: String by project
val lombokVersion: String by project
val mapstructVersion: String by project
val mockitoJunitJupiterVersion: String by project
val postgresqlVersion: String by project
val liquibaseCoreVersion: String by project
//val preliquibaseVersion: String by project

dependencies {
    implementation(project(":users-util"))
    implementation(project(":users-usecase"))

    implementation("org.springframework.boot:spring-boot-starter-data-jpa:$springBootVersion")
    //implementation("net.lbruun.springboot:preliquibase-spring-boot-starter:$preliquibaseVersion")
    implementation("org.postgresql:postgresql:$postgresqlVersion")
    implementation("com.zaxxer:HikariCP")
    implementation("org.liquibase:liquibase-core:$liquibaseCoreVersion")

    implementation("org.projectlombok:lombok:$lombokVersion")
    testImplementation("org.projectlombok:lombok:$lombokVersion")
    implementation("org.mapstruct:mapstruct:$mapstructVersion")
    annotationProcessor("org.projectlombok:lombok-mapstruct-binding:$lombokMapstructBindingVersion")
    annotationProcessor("org.mapstruct:mapstruct-processor:$mapstructVersion")
    annotationProcessor("org.projectlombok:lombok:$lombokVersion")

    testImplementation("org.mockito:mockito-junit-jupiter:$mockitoJunitJupiterVersion")
    testImplementation("org.mockito:mockito-core")
    testImplementation("org.springframework.boot:spring-boot-starter-test:$springBootVersion")
    testImplementation("org.junit.jupiter:junit-jupiter-api:$junitJupiterApiVersion")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:$junitJupiterApiVersion")
    testImplementation("org.testcontainers:postgresql:1.18.3")
}
