package com.jl.blog.database.dao

import androidx.room.*
import com.jl.blog.database.entities.BlogEntity
import com.jl.blog.domain.model.Blog

@Dao
interface BlogDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun updateData(list: List<BlogEntity>?)

    @Query("SELECT * FROM blog_table ORDER BY date DESC")
    suspend fun getAllBlogs(): List<BlogEntity>

    @Query("DELETE FROM blog_table")
    suspend fun deleteAllBlogs()

}