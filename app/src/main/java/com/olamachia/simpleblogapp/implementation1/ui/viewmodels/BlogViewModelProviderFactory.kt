package com.olamachia.simpleblogapp.implementation1.ui.viewmodels

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.olamachia.simpleblogapp.implementation1.repository.BlogRepository

class BlogViewModelProviderFactory(
    val app: Application,

    private val repository: BlogRepository
    ): ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return BlogViewModel(app, repository) as T
    }
}