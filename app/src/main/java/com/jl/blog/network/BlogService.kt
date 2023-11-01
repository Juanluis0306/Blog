package com.jl.blog.network

import com.jl.blog.model.Items
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class BlogService @Inject constructor(private val api: ApiInterface) {

    suspend fun getBlogs(): List<Items> {
        return withContext(Dispatchers.IO) {
            val response = api.getAllBlogsUser()
            response.body() ?: emptyList()
        }
    }

}