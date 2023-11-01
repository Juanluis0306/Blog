package com.jl.blog.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.jl.blog.domain.model.Blog

@Entity(tableName = "blog_table")
class BlogEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id") val id: Int = 0,
    @ColumnInfo(name = "title")
    var title: String,
    @ColumnInfo(name = "content")
    var content: String,
    @ColumnInfo(name = "date")
    var date: String,
    @ColumnInfo(name = "author")
    var author: String
)

fun Blog.toDatabase() = BlogEntity(title = title, content = content, date = date, author = author)