package com.justai.training.scenario

import com.justai.jaicf.builder.createModel
import com.justai.jaicf.model.scenario.ScenarioModel
import com.justai.jaisl.scenario.JaislScenario
import com.justai.jaisl.service.ScenarioBuilder
import org.springframework.stereotype.Component

@Component
class JaislBuyElephantScenario(
    override var scenarioBuilder: ScenarioBuilder,
) : JaislScenario {
    override val model: ScenarioModel = createModel {
        buildScenario()
    }
}
