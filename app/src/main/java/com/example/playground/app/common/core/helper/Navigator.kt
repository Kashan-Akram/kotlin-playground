package com.example.playground.app.common.core.helper

import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class Navigator @Inject constructor() {

    private val _route = MutableSharedFlow<Any>(
        replay = 0,
        extraBufferCapacity = 5,
        onBufferOverflow = BufferOverflow.DROP_OLDEST
    )

    val route: SharedFlow<Any> = _route.asSharedFlow()

    fun <T : Any> navigateTo(event: T) {
        _route.tryEmit(event)
    }

}