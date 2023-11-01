package com.jl.blog.domain

import com.jl.blog.database.BlogRepository
import com.jl.blog.database.dao.BlogDao
import com.jl.blog.database.entities.BlogEntity
import com.jl.blog.database.entities.toDatabase
import com.jl.blog.domain.model.Blog
import javax.inject.Inject

class GetBlogsUseCase @Inject constructor(private val repository: BlogRepository) {
    suspend operator fun invoke(): List<Blog> {
        val blogs = repository.getAllBlogsFromApi()

        return if (blogs.isNotEmpty()) {
            repository.deleteBlogs()
            repository.updateData(blogs.map { it.toDatabase() })
            blogs
        } else {
            repository.getAllBlogs()
        }
    }
}