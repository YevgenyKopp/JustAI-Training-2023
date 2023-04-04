package com.justai.training.configuration

import com.justai.jaicf.BotEngine
import com.justai.jaicf.activator.caila.CailaIntentActivator
import com.justai.jaicf.activator.caila.CailaNLUSettings
import com.justai.jaicf.activator.catchall.CatchAllActivator
import com.justai.jaicf.activator.regex.RegexActivator
import com.justai.jaicf.channel.jaicp.JaicpPollingConnector
import com.justai.jaicf.channel.jaicp.channels.ChatWidgetChannel
import com.justai.jaicf.channel.jaicp.channels.TelephonyChannel
import com.justai.jaicf.channel.jaicp.logging.JaicpConversationLogger
import com.justai.jaicf.logging.Slf4jConversationLogger
import com.justai.training.properties.JaicpProperties
import com.justai.training.scenario.JaicfBuyElephantScenario
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Primary
import org.springframework.context.annotation.Profile

@Suppress("unused")
@Configuration
@Primary
class ConnectionConfiguration(
    private var jaicpProperties: JaicpProperties,
) {
    @Profile("dev")
    @Bean
    fun getPollerConnection() =
        JaicpPollingConnector(
            botApi = getBotEngine(),
            url = jaicpProperties.url,
            accessToken = jaicpProperties.accessToken,
            channels = listOf(
                TelephonyChannel,
                ChatWidgetChannel
            )
        )

    @Bean
    fun getBotEngine() =
        BotEngine(
            scenario = JaicfBuyElephantScenario,
            activators = arrayOf(
                CailaIntentActivator.Factory(
                    CailaNLUSettings(
                        accessToken = jaicpProperties.caila.accessToken,
                        confidenceThreshold = jaicpProperties.caila.confidenceThreshold,
                        cailaSlotFillingSettings = jaicpProperties.caila.cailaSlotFillingSettings,
                        classifierNBest = jaicpProperties.caila.classifierNBest,
                        cailaUrl = jaicpProperties.caila.cailaUrl
                    )
                ),
                RegexActivator,
                CatchAllActivator
            ),
            conversationLoggers = arrayOf(
                JaicpConversationLogger(jaicpProperties.caila.accessToken),
                Slf4jConversationLogger()
            )
        )
}