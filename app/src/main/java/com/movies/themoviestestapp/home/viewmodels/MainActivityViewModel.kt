package com.movies.themoviestestapp.home.viewmodels

import androidx.lifecycle.ViewModel
import com.movies.themoviestestapp.utils.OneTimeLiveData
import com.movies.themoviestestapp.utils.emit
import org.koin.core.component.KoinComponent

class MainActivityViewModel : ViewModel(), KoinComponent {

    private val _title = OneTimeLiveData<String>()
    val observeTitle
        get() = _title.emit()

    private val _showBackButton = OneTimeLiveData<Boolean>()
    val observeShowBackButton
        get() = _showBackButton.emit()

    fun setTitle(value: String) {
        _title.postValue(value)
    }

    fun showBackButton(value: Boolean) {
        _showBackButton.postValue(value)
    }
}
