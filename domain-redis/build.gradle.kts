import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
	id("org.springframework.boot")
	id("io.spring.dependency-management")
	id("java")
	kotlin("jvm")
	kotlin("plugin.spring")
	kotlin("plugin.jpa")


}

java.sourceCompatibility = JavaVersion.VERSION_11

configurations {
	compileOnly {
		extendsFrom(configurations.annotationProcessor.get())
	}
}

dependencies {
	implementation(project(":core"))

	implementation("org.springframework.boot:spring-boot-starter-data-redis")
	implementation("org.springframework.integration:spring-integration-redis")
	implementation("io.lettuce:lettuce-core")

	implementation("org.apache.commons:commons-pool2")

	implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
	implementation("org.jetbrains.kotlin:kotlin-reflect")
	implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
	developmentOnly("org.springframework.boot:spring-boot-devtools")
	runtimeOnly("org.mariadb.jdbc:mariadb-java-client")
	annotationProcessor("org.springframework.boot:spring-boot-configuration-processor")
	testImplementation("org.springframework.boot:spring-boot-starter-test")
}