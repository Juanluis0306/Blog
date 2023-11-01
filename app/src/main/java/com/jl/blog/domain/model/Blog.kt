package com.jl.blog.domain.model

import com.jl.blog.database.entities.BlogEntity
import com.jl.blog.model.Items

data class Blog(
    val title: String,
    val content: String,
    val date: String,
    val author: String
)

fun Items.toDomain() = Blog(title ?: "", content ?: "", published ?: "", author?.displayName ?: "")
fun BlogEntity.toDomain() = Blog(title, content, date, author)