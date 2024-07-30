package com.helloworld.rds

import org.testcontainers.containers.MySQLContainer
import org.testcontainers.utility.DockerImageName

object RdsContainer {
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
}
