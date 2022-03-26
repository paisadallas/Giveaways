package com.john.giveaways.util

sealed class GiveawaysStates {
    object LOADING: GiveawaysStates()
    class SUCCESS<T>(val giveaways: T):GiveawaysStates()
    class ERROR(val error : Throwable):GiveawaysStates()
}