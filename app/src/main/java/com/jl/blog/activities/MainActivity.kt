package com.jl.blog.activities

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.WindowInsets
import androidx.activity.viewModels
import android.view.WindowInsetsController
import android.view.WindowManager
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.lifecycle.LiveData
import androidx.recyclerview.widget.LinearLayoutManager
import com.jl.blog.R
import com.jl.blog.adapter.BlogAdapter
import com.jl.blog.database.entities.BlogEntity
import com.jl.blog.databinding.ActivityMainBinding
import com.jl.blog.domain.model.Blog
import com.jl.blog.model.ResponseBlogs
import com.jl.blog.utils.RecyclerItemClickListener
import com.jl.blog.viewmodel.RegisterViewModel
import dagger.hilt.android.AndroidEntryPoint
import java.util.*

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private var mAdapter: BlogAdapter? = null
    private var list: MutableList<BlogEntity>? = null
    private val viewModel: RegisterViewModel by viewModels()
    private var blogEntitySelect: BlogEntity? = null
    private var listBlogEntity: ArrayList<Blog> = arrayListOf()
    private var listBlogsUser: LiveData<ResponseBlogs>? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        @Suppress("DEPRECATION")
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            val controller = window.insetsController

            if (controller != null) {
                controller.hide(WindowInsets.Type.statusBars() or WindowInsets.Type.navigationBars())
                controller.systemBarsBehavior =
                    WindowInsetsController.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE
            }
        } else {
            window.setFlags(
                WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN
            )
        }
        viewModel.onCreate()
        listeners()
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun listeners() {
        binding.fab.setOnClickListener {
            val i = Intent(this, RegisterActivity::class.java)
            startActivity(i)
        }
        mAdapter = BlogAdapter(listBlogEntity, this)
        binding.rvTask.adapter = mAdapter
        binding.rvTask.layoutManager = LinearLayoutManager(this)
        viewModel.isLoading.observe(this) {
            binding.loading.isVisible = it
        }

        viewModel.listBlogEntity.observe(this) {
            listBlogEntity.clear()
            listBlogEntity.addAll(it)
            Log.i("Tag123", "Valores insertados correctamente  *********  " + listBlogEntity.size)
            if (listBlogEntity.isEmpty()) {
                binding.rvTask.visibility = View.GONE
                binding.txtListEmpty.visibility = View.VISIBLE
            } else {
                binding.rvTask.visibility = View.VISIBLE
                binding.txtListEmpty.visibility = View.GONE
                mAdapter!!.notifyDataSetChanged()
            }
        }
        binding.rvTask.addOnItemTouchListener(RecyclerItemClickListener(this) { view, position ->
            val btnMenu: ImageView = view.findViewById(R.id.btnMenu)
            btnMenu.setOnClickListener {

            }
        }
        )
    }


    override fun onBackPressed() {
    }
}