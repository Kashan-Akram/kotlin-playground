package com.example.playground.app.common.presentation.screen

sealed interface HomeEvents {

    data object NavigateToNews : HomeEvents

}