package com.jl.blog.database

import com.jl.blog.database.dao.BlogDao
import com.jl.blog.database.entities.BlogEntity
import com.jl.blog.domain.model.Blog
import com.jl.blog.domain.model.toDomain
import com.jl.blog.model.Items
import com.jl.blog.network.BlogService
import javax.inject.Inject

class BlogRepository @Inject constructor(
    private val api: BlogService,
    private val blogDao: BlogDao
) {

    suspend fun getAllBlogsFromApi(): List<Blog> {
        val response: List<Items> = api.getBlogs()
        return response.map { it.toDomain() }
    }

    fun updateData(listBlogEntity: List<BlogEntity>?) {
        blogDao.updateData(listBlogEntity)
    }

    suspend fun getAllBlogs(): List<Blog> {
        val list: ArrayList<Blog> = arrayListOf()
        blogDao.getAllBlogs().forEach {
            list.add(Blog(it.title, it.content, it.date, it.author))
        }
        return list
    }

    suspend fun deleteBlogs() {
        blogDao.deleteAllBlogs()
    }

}