package com.jl.blog.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.jl.blog.databinding.ActivityShowDetailBinding

class ShowDetailActivity : AppCompatActivity() {
    lateinit var binding: ActivityShowDetailBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityShowDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        listeners()
    }

    private fun listeners() {
        binding.txtAuthor.text = intent.getStringExtra("EXTRA_BLOG_AUTHOR")
        binding.txtTitle.text = intent.getStringExtra("EXTRA_BLOG_TITLE")
        binding.txtContent.text = intent.getStringExtra("EXTRA_BLOG_CONTENT")

        binding.btnReturn.setOnClickListener {
            finish()
        }
    }
}