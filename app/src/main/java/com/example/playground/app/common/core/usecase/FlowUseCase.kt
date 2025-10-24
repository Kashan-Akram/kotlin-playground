package com.example.playground.app.common.core.usecase

import com.example.playground.app.common.core.models.Resultt
import kotlinx.coroutines.flow.Flow

data object NoFlowRequestValues : FlowUseCase.RequestValues

abstract class FlowUseCase<Q : FlowUseCase.RequestValues, R> {

    fun run(requestValues : Q) : Flow<Resultt<R>> {
        return executeUseCase(requestValues)
    }

    protected abstract fun executeUseCase(requestValues : Q) : Flow<Resultt<R>>

    interface RequestValues

}