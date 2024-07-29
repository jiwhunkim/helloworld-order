package com.helloworld.rds

import io.kotest.core.config.AbstractProjectConfig
import org.testcontainers.containers.MySQLContainer
import org.testcontainers.utility.DockerImageName

object RdsContainer : AbstractProjectConfig() {
    val rdsContainer =
        MySQLContainer<Nothing>(DockerImageName.parse("mysql:latest")).apply {
            withUrlParam("characterEncoding", "UTF-8")
            withUrlParam("useUnicode", "true")
            withUrlParam("sslMode", "DISABLED")
            withUrlParam("useSSL", "false")
            withUrlParam("serverTimezone", "Asia/Seoul")
            withUsername("root")
            withPassword("")
            withCommand("--character-set-server=utf8mb4 --collation-server=utf8mb4_general_ci --character-set-filesystem=utf8mb4")
        }

    override suspend fun beforeProject() {
        super.beforeProject()
        if (rdsContainer.isRunning) {
            System.setProperty("spring.datasource.hikari.driver-class-name", rdsContainer.driverClassName)
            System.setProperty("spring.datasource.url", rdsContainer.jdbcUrl)
            System.setProperty("spring.datasource.username", rdsContainer.username)
            System.setProperty("spring.datasource.password", rdsContainer.password)
        }
    }
}
