package com.justai.training.properties

import com.justai.jaicf.activator.caila.CailaNLUSettings
import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.context.properties.ConstructorBinding

@ConstructorBinding
@ConfigurationProperties(prefix = "jaicp")
data class JaicpProperties(
    var url: String,
    var accessToken: String,
    var caila: CailaNLUSettings
)
