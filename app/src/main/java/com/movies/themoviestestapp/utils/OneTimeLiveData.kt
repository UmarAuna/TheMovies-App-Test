package com.movies.themoviestestapp.utils

import androidx.lifecycle.MutableLiveData

/**
 * Use this liveData to emit data only once
 */
open class OneTimeLiveData<T> : MutableLiveData<Event<T>> {

    constructor() : super()

    constructor(value: T) : super(Event(value))

    @get:JvmName("value")
    @set:JvmName("value")
    var value: T?
        get() {
            return getValue()?.peekContent()
        }
        set(value) {
            if (value != null) {
                setValue(Event(value))
            }
        }

    @JvmName("__postValue")
    fun postValue(value: T) {
        super.postValue(Event(value))
    }
}
