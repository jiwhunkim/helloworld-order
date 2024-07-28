import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
	kotlin("jvm") version "1.9.23"
}

repositories {
	gradlePluginPortal()
	mavenCentral()
}

allprojects {
	group = "com.helloworld"
	version = "0.0.1-SNAPSHOT"

	tasks.withType<KotlinCompile> {
		kotlinOptions {
			freeCompilerArgs = listOf("-Xjsr305=strict")
			jvmTarget = "21"
		}
	}

	tasks.withType<Test> {
		useJUnitPlatform()
	}
}

subprojects {
	apply(plugin = "java")

	dependencies {
		testImplementation(platform("org.junit:junit-bom:5.10.3"))
		testImplementation("org.junit.jupiter:junit-jupiter")
	}
}