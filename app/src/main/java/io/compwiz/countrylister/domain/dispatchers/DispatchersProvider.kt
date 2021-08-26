package io.compwiz.countrylister.domain.dispatchers

import kotlinx.coroutines.CoroutineDispatcher

interface DispatchersProvider {
    fun io(): CoroutineDispatcher
    fun main(): CoroutineDispatcher
}