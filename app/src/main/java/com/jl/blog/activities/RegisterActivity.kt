package com.jl.blog.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.jl.blog.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RegisterActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
    }
}