package com.justai.training.scenario

import com.justai.jaicf.activator.caila.cailaEntity
import com.justai.jaicf.builder.Scenario
import com.justai.jaicf.channel.jaicp.reactions.telephony
import com.justai.training.extensions.elephantAmount
import com.justai.training.extensions.elephantType
import com.justai.training.extensions.getEntity
import com.justai.training.extensions.stateInARowCounter
import com.justai.training.model.City
import com.justai.training.model.ElephantType
import com.justai.training.services.ElephantService

val JaicfBuyElephantScenario = Scenario {
    state("Hangup") {
        action {
            reactions.telephony?.hangup()
        }
    }

    state("Hello") {
        activators {
            regex("/start")
            intent("Hello")
        }
        action {
            reactions.telephony?.startNewSession()
            reactions.sayRandom("Привет, купите слона!", "Приветствую! Как насчёт того, чтобы слона приобрести?")
        }

        state("Price", noContext = true) {
            activators {
                intent("Price")
            }
            action {
                reactions.say("Слон стоит всего 200\$. Берёте?")
            }
        }

        state("BuyElephant") {
            activators {
                cailaEntity("Agree")
            }
            action {
                reactions.say("Какой именно слон вас интересует?")
            }

            state("GetElephantType") {
                activators {
                    cailaEntity("ElephantType")
                }
                action {
                    context.elephantType = getEntity<ElephantType>()

                    if (ElephantService().isElephantAvailable(context.elephantType?.code)) {
                        reactions.say("Сколько слонов вам нужно")
                    } else {
                        reactions.say("Таких слонов у нас нет. Назовите других!")
                        reactions.changeState("..")
                    }
                }

                state("GetElephantNumber") {
                    activators {
                        cailaEntity("duckling.number")
                    }
                    action {
                        context.elephantAmount = activator.cailaEntity?.value?.toIntOrNull()

                        reactions.say("И последнее, в какой город нужно доставить?")
                    }

                    state("GetCity") {
                        activators {
                            cailaEntity("City")
                        }
                        action {
                            val city = getEntity<City>()
                            reactions.say("Итак, вы заказали ${context.elephantType?.genitiveName} слона в количестве ${context.elephantAmount} шт. в ${city?.name}. Ожидайте доставки!")
                            reactions.telephony?.hangup()
                            reactions.go("/Hangup")
                        }
                    }
                }

                fallback {
                    val counter = context.stateInARowCounter

                    if (counter < 2) {
                        reactions.say("Не совсем понял. Назовите необходимое количество слонов.")
                    } else {
                        reactions.say("Не смог осознать, сколько слонов вам требуется. Перезвоните, когда определитесь.")
                        reactions.telephony?.hangup()
                    }

                    context.stateInARowCounter = counter + 1
                }
            }
        }

        fallback {
            reactions.say("Все говорят ${request.input}, а ты купи слона!")
        }
    }
}