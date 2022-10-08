package com.movies.themoviestestapp.utils

class EventLiveData : OneTimeLiveData<Unit>() {

    fun post() {
        super.postValue(Unit)
    }
}