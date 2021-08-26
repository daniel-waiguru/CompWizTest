package io.compwiz.countrylister.base

import io.compwiz.countrylister.util.KoinTestRule
import org.junit.Rule
import org.koin.test.KoinTest

abstract class BaseKoinTest: KoinTest {
    @get:Rule
    val koinTestRule = KoinTestRule()
}