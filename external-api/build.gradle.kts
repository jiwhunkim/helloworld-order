plugins {
	id("org.springframework.boot")
	id("io.spring.dependency-management")
	kotlin("jvm")
	kotlin("plugin.spring")
}

java.sourceCompatibility = JavaVersion.VERSION_11

configurations {
	compileOnly {
		extendsFrom(configurations.annotationProcessor.get())
	}
}

dependencies {
	implementation(project(":core"))
	implementation(project(":core-web"))
	implementation(project(":domain-mapper"))
	implementation(project(":domain-redis"))
	implementation(project(":domain-rds"))
	implementation(project(":domain"))

//	implementation(project(":domain")) {
//		exclude(group="org.springframework", module = "spring-tx")
//	}
//	implementation(project(":domain-redis")) {
//		exclude(group="org.springframework", module = "spring-tx")
//	}
//	implementation(project(":domain-rds")) {
//		exclude(group="org.springframework", module = "spring-tx")
//	}
//
//	implementation("org.springframework:spring-tx")

	implementation("org.springframework.boot:spring-boot-starter-web")
	implementation("org.springframework.boot:spring-boot-starter-security")

	implementation("io.springfox:springfox-boot-starter:3.0.0")
	implementation("io.springfox:springfox-swagger-ui:3.0.0")
}