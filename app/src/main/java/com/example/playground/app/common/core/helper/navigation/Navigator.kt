package com.example.playground.app.common.core.helper.navigation

import androidx.navigation.NavOptionsBuilder
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class Navigator @Inject constructor() {
    private val _navEvents = MutableSharedFlow<NavEvent<Any>>(
        replay = 0,
        extraBufferCapacity = 5,
        onBufferOverflow = BufferOverflow.DROP_OLDEST
    )

    val navEvents: SharedFlow<NavEvent<Any>> = _navEvents.asSharedFlow()

    fun <T : Any> navigateTo(
        route: T,
        options: (NavOptionsBuilder.() -> Unit)? = null
    ) {
        _navEvents.tryEmit(NavEvent(route, options))
    }
}

data class NavEvent<T : Any>(
    val route: T,
    val options: (NavOptionsBuilder.() -> Unit)? = null
)