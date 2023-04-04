package com.justai.training.extensions

import com.justai.jaicf.context.BotContext
import com.justai.training.model.ElephantType

//JAICF
var BotContext.stateInARowCounter
    get() = session["${dialogContext.currentState}InARowCounter"] as? Int ?: 0
    set(value) {
        session["${dialogContext.currentState}InARowCounter"] = value
    }

//JAICF
var BotContext.elephantType: ElephantType?
    get() = session["ElephantType"] as? ElephantType
    set(value) {
        session["ElephantType"] = value
    }

//JAICF
var BotContext.elephantAmount: Int?
    get() = session["ElephantAmount"] as? Int
    set(value) {
        session["ElephantAmount"] = value
    }