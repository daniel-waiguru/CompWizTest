package io.compwiz.countrylister.util

import io.compwiz.countrylister.data.di.appModule
import io.compwiz.countrylister.data.di.networkingModule
import io.compwiz.countrylister.presentation.di.viewModelModule
import org.junit.rules.TestRule
import org.junit.runner.Description
import org.junit.runners.model.Statement
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin

class KoinTestRule: TestRule {
    override fun apply(base: Statement?, description: Description?): Statement {
        return object : Statement() {
            override fun evaluate() {
                stopKoin()
                startKoin {
                    modules(listOf(networkingModule, viewModelModule, appModule))
                }
                base?.evaluate()
                stopKoin()
            }
        }
    }
}