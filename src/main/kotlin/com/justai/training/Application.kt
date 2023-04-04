package com.justai.training

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.ConfigurationPropertiesScan
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.boot.runApplication

@SpringBootApplication(scanBasePackages = (["com.justai.training"]))
@EnableConfigurationProperties
@ConfigurationPropertiesScan("com.justai.training")
class Application

fun main(args: Array<String>) {
    runApplication<Application>(*args)
}