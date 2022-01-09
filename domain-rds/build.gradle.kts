plugins {
	kotlin("plugin.jpa")
}

dependencies {
	runtimeOnly("org.mariadb.jdbc:mariadb-java-client")

	implementation("javax.annotation:javax.annotation-api:1.3.2")
	kapt("jakarta.persistence:jakarta.persistence-api")
	kapt("javax.annotation:javax.annotation-api:1.3.2")

	kapt(group = "com.querydsl", name = "querydsl-apt", classifier = "jpa")
}

allOpen {
	annotation("javax.persistence.Entity")
	annotation("javax.persistence.Embeddable")
	annotation("javax.persistence.MappedSuperclass")
}

val jar: Jar by tasks
val bootJar: org.springframework.boot.gradle.tasks.bundling.BootJar by tasks

bootJar.enabled = false
jar.enabled = true