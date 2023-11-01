package com.jl.blog.viewmodel

import android.app.Application
import androidx.lifecycle.*
import com.jl.blog.database.entities.BlogEntity
import com.jl.blog.database.BlogRepository
import com.jl.blog.domain.GetBlogsUseCase
import com.jl.blog.domain.model.Blog
import com.jl.blog.model.Items
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor(
    private val getQuotesUseCase: GetBlogsUseCase
    ) : ViewModel() {

    var listBlogEntity = MutableLiveData<List<Blog>>()
    val isLoading = MutableLiveData<Boolean>()

    fun onCreate() {
        viewModelScope.launch {
            isLoading.postValue(true)
            val result = getQuotesUseCase()

            if (result.isNotEmpty()) {
                listBlogEntity.postValue(result)
                isLoading.postValue(false)
            }
        }
    }

}