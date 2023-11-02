package com.jl.blog.network

import com.jl.blog.model.Items
import com.jl.blog.model.ResponseBlogs
import com.jl.blog.model.ResponseSaveBlog
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST

interface ApiInterface {
    @GET("blogs/2399953/posts?key=AIzaSyA3bSL28LweT6C8OYN9JrGaucqpMA8sciI")
    suspend fun getAllBlogsUser(): Response<ResponseBlogs>

    @POST("/blogs/8070105920543249955/posts/")
    suspend fun saveBlog(@Header("Authorization") key: String): Response<ResponseSaveBlog>
}