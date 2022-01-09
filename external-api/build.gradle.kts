plugins {
}

dependencies {
	implementation(project(":core"))
	implementation(project(":core-web"))
	implementation(project(":domain-redis"))
	implementation(project(":domain-rds"))
	implementation(project(":domain"))

	implementation("org.springframework.boot:spring-boot-starter-web")
	implementation("org.springframework.boot:spring-boot-starter-actuator")
	implementation("org.springframework.boot:spring-boot-starter-security")
}