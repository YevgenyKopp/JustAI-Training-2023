package com.justai.training.services

import com.justai.jaicf.context.BotContext
import com.justai.jaisl.integration.JaislIntegration
import com.justai.training.model.ElephantType.ElephantCode
import com.justai.training.model.ElephantType.ElephantCode.AFR
import org.springframework.stereotype.Component

//JAISL
@Component
class ElephantService: JaislIntegration {
    fun isElephantAvailable(type: ElephantCode?) = type == AFR
    override fun getIntegrationResult(context: BotContext): Result<Map<String, Any?>?> {
        (ElephantCode.valueOf(context.session["ElephantCode"] as String)).let {
            return Result.success(mapOf("isElephantAvailable" to isElephantAvailable(it)))
        }
    }
}