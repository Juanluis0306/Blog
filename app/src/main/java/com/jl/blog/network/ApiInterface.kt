package com.jl.blog.network

import com.jl.blog.model.Items
import com.jl.blog.model.ResponseBlogs
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET

interface ApiInterface {
    @GET("blogs/2399953/posts?key=AIzaSyA3bSL28LweT6C8OYN9JrGaucqpMA8sciI")
    fun getAllBlogsUser(): Response<List<Items>>
}