plugins {
  java
}

dependencies {
    implementation(project(":users-dao"))
    implementation(project(":users-domain"))

    testImplementation("org.springframework.boot:spring-boot-starter-test")
}
