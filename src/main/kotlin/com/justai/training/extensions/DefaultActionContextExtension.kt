package com.justai.training.extensions

import com.google.gson.Gson
import com.justai.jaicf.activator.caila.cailaEntity
import com.justai.jaicf.context.DefaultActionContext

inline fun <reified T> DefaultActionContext.getEntity(): T? = activator.cailaEntity?.value?.let {
    runCatching { Gson().fromJson(it, T::class.java) }.getOrNull()
}