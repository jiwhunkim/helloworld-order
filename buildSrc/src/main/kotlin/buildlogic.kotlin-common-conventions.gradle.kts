plugins {
    kotlin("jvm")
}

repositories {
    mavenCentral()
    gradlePluginPortal()
}

dependencies {
    implementation(kotlin("reflect"))
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.8.0")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-reactor:1.8.0")
}

kotlin {
    compilerOptions {
        apiVersion.set(org.jetbrains.kotlin.gradle.dsl.KotlinVersion.KOTLIN_1_9)
        freeCompilerArgs.add("-Xjsr305=strict")
    }
}

tasks.withType<ProcessResources> {
    // 동일한 파일(main/resources/application.yaml, intTest/resources/application.yaml)이 있어서, 리소스 복사할 때 충돌 회피
    // Execution failed for task ':web:processIntTestResources'.
    // > Entry application.yaml is a duplicate but no duplicate handling strategy has been set.
    duplicatesStrategy = DuplicatesStrategy.INCLUDE
}
