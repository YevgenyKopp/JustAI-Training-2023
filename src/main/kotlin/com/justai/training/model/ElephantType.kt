package com.justai.training.model

import kotlinx.serialization.Serializable

//JAICF
@Serializable
data class ElephantType(
    val name: String,
    val code: ElephantCode,
    val genitiveName: String,
) {
    enum class ElephantCode {
        AFR, IND
    }
}