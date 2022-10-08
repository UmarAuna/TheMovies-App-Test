package com.movies.themoviestestapp.utils

import androidx.annotation.NonNull
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations

@NonNull
fun <T> MutableLiveData<Event<T>>.emit(): LiveData<T> {
    return Transformations.switchMap(this) { event ->
        val hasBeenHandled = event.hasBeenHandled
        val content = event.getContentIfNotHandled()
        val liveData = Transformations.map(this) { content }
        when {
            hasBeenHandled -> liveData.nonNull()
            else -> liveData
        }
    }
}

@JvmName("toLiveData")
fun <T> MutableLiveData<T>.emit(): LiveData<T> {
    return this
}

fun <T> MutableLiveData<T>.refresh() {
    postValue(this.value)
}

fun <T> LiveData<T?>.nonNull(): LiveData<T> {
    val mediator = MediatorLiveData<T>()
    mediator.addSource(this) { value ->
        value?.let {
            mediator.value = it
        }
    }
    return mediator
}
