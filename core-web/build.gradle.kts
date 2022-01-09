plugins {
}

dependencies {
	implementation(Libs.SpringBoot.starterWeb)
}

val jar: Jar by tasks
val bootJar: org.springframework.boot.gradle.tasks.bundling.BootJar by tasks

bootJar.enabled = false
jar.enabled = true