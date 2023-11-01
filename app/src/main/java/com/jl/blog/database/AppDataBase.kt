package com.jl.blog.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.jl.blog.database.dao.BlogDao
import com.jl.blog.database.entities.BlogEntity

@Database(entities = [BlogEntity::class], version = 1)
abstract class AppDataBase: RoomDatabase() {
    abstract fun getBlogDao(): BlogDao
}