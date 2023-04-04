package com.justai.training.services

import com.justai.training.model.ElephantType.ElephantCode
import com.justai.training.model.ElephantType.ElephantCode.AFR
import org.springframework.stereotype.Component

@Component
class ElephantService {
    fun isElephantAvailable(type: ElephantCode?) = type == AFR
}