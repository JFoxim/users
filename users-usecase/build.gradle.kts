plugins {
  java
}

val lombokMapstructBindingVersion: String by project
val junitJupiterApiVersion: String by project
val lombokVersion: String by project
val mapstructVersion: String by project

dependencies {
    implementation(project(":users-domain"))

    implementation("org.projectlombok:lombok:$lombokVersion")
    testImplementation("org.projectlombok:lombok:$lombokVersion")
    implementation("org.mapstruct:mapstruct:$mapstructVersion")
    annotationProcessor("org.projectlombok:lombok-mapstruct-binding:$lombokMapstructBindingVersion")
    annotationProcessor("org.mapstruct:mapstruct-processor:$mapstructVersion")
    annotationProcessor("org.projectlombok:lombok:$lombokVersion")

    testImplementation("org.mockito:mockito-junit-jupiter:5.2.0")
    testImplementation("org.junit.jupiter:junit-jupiter-api:$junitJupiterApiVersion")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:$junitJupiterApiVersion")
}