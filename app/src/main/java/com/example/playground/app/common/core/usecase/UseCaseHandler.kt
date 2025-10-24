package com.example.playground.app.common.core.usecase

import com.example.playground.app.common.core.models.Resultt
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.withContext

class UseCaseHandler private constructor() {

    suspend fun <Q : SuspendUseCase.RequestValues, R> execute(
        values: Q,
        useCase: SuspendUseCase<Q, R>,
        dispatcher: CoroutineDispatcher = Dispatchers.IO
    ): Resultt<R> {
        return withContext(dispatcher) {
            useCase.run(values)
        }
    }

    fun <Q : SuspendUseCase.RequestValues, R> executeAsync(
        values: Q,
        useCase: SuspendUseCase<Q, R>,
        scope: CoroutineScope,
        dispatcher: CoroutineDispatcher = Dispatchers.IO
    ): Deferred<Resultt<R>> {
        return scope.async(dispatcher) {
            useCase.run(values)
        }
    }

    fun <Q : FlowUseCase.RequestValues, R> executeFlow(
        values: Q,
        useCase: FlowUseCase<Q, R>,
        dispatcher: CoroutineDispatcher = Dispatchers.IO
    ): Flow<Resultt<R>> {
        return useCase.run(values).flowOn(dispatcher)
    }

    companion object {

        @Volatile
        private var INSTANCE: UseCaseHandler? = null

        fun getInstance(): UseCaseHandler {
            return INSTANCE ?: synchronized(this@Companion) {
                INSTANCE ?: UseCaseHandler().also { INSTANCE = it }
            }
        }

    }

}