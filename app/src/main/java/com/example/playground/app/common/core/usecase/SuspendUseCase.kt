package com.example.playground.app.common.core.usecase

import com.example.playground.app.common.core.models.Resultt

data object NoRequestValues : SuspendUseCase.RequestValues

abstract class SuspendUseCase<Q : SuspendUseCase.RequestValues, R> {

    suspend fun run(requestValues : Q) : Resultt<R> {
        return executeUseCase(requestValues)
    }

    protected abstract suspend fun executeUseCase(requestValues : Q) : Resultt<R>

    interface RequestValues

}