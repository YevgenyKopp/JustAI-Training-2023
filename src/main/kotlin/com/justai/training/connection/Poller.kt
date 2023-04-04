package com.justai.training.connection

import com.justai.jaicf.channel.jaicp.JaicpPollingConnector
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.springframework.boot.ApplicationArguments
import org.springframework.boot.ApplicationRunner
import org.springframework.context.annotation.Profile
import org.springframework.stereotype.Component
import kotlin.coroutines.CoroutineContext

@Profile("dev", "qa")
@Component
@Suppress("unused")
class Poller(
    private val jaicpPollingConnector: JaicpPollingConnector,
) : ApplicationRunner, CoroutineScope {
    override val coroutineContext: CoroutineContext =
        Dispatchers.Default

    override fun run(args: ApplicationArguments?) {
        launch { jaicpPollingConnector.runBlocking() }
    }
}