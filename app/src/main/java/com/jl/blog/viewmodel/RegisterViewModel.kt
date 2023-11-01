package com.jl.blog.viewmodel

import android.app.Application
import androidx.lifecycle.*
import com.jl.blog.database.entities.BlogEntity
import com.jl.blog.database.BlogRepository
import com.jl.blog.domain.GetBlogsLocalUseCase
import com.jl.blog.domain.GetBlogsUseCase
import com.jl.blog.domain.model.Blog
import com.jl.blog.model.Items
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor(
    private val getQuotesUseCase: GetBlogsUseCase,
    private val getBlogsLocalUseCase: GetBlogsLocalUseCase
) : ViewModel() {

    var listBlogEntity = MutableLiveData<List<Blog>>()
    val isLoading = MutableLiveData<Boolean>()

    fun onCreate(isConnection: Boolean) {
        if (isConnection) {
            viewModelScope.launch {
                isLoading.postValue(true)
                val result = getQuotesUseCase()
                if (result.isNotEmpty()) {
                    listBlogEntity.postValue(result)
                    isLoading.postValue(false)
                }
            }
        } else {
            viewModelScope.launch {
                isLoading.postValue(true)
                val result = getBlogsLocalUseCase()
                if (result.isNotEmpty()) {
                    listBlogEntity.postValue(result)
                    isLoading.postValue(false)
                }
            }
        }
    }

}