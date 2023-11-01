package com.jl.blog.domain

import com.jl.blog.database.BlogRepository
import com.jl.blog.database.entities.toDatabase
import com.jl.blog.domain.model.Blog
import javax.inject.Inject

class GetBlogsLocalUseCase @Inject constructor(private val repository: BlogRepository) {
    suspend operator fun invoke(): List<Blog> {
        return repository.getAllBlogs()
    }
}