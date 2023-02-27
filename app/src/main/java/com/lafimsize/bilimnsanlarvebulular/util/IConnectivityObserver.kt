package com.lafimsize.bilimnsanlarvebulular.util

import kotlinx.coroutines.flow.Flow

interface IConnectivityObserver {

    fun networkObserver(): Flow<status>


    enum class status{
        Available,
        Lost,
        Unavailable,
        Losing
    }

}