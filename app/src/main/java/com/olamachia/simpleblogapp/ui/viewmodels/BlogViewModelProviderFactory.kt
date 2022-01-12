package com.olamachia.simpleblogapp.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.olamachia.simpleblogapp.repository.BlogRepository

class BlogViewModelProviderFactory(
    private val repository: BlogRepository
    ): ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return BlogViewModel(repository) as T
    }
}