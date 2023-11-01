package com.jl.blog.activities

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
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
import androidx.appcompat.widget.SearchView
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import cn.pedant.SweetAlert.SweetAlertDialog
import com.jl.blog.R
import com.jl.blog.adapter.BlogAdapter
import com.jl.blog.database.entities.BlogEntity
import com.jl.blog.databinding.ActivityMainBinding
import com.jl.blog.domain.model.Blog
import com.jl.blog.utils.RecyclerItemClickListener
import com.jl.blog.viewmodel.RegisterViewModel
import dagger.hilt.android.AndroidEntryPoint
import java.util.*

@AndroidEntryPoint
class MainActivity : AppCompatActivity(), BlogAdapter.OnItemClickListener {

    private lateinit var binding: ActivityMainBinding
    private var mAdapter: BlogAdapter? = null
    private val viewModel: RegisterViewModel by viewModels()
    private var blogEntitySelect: BlogEntity? = null
    private var listBlogEntity: ArrayList<Blog> = arrayListOf()

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
        val isConnection = isNetworkAvailable(this)
        if (!isNetworkAvailable(this)) {
            showErrorRed()
            binding.fab.visibility = View.GONE
        }
        viewModel.onCreate(isConnection)
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
            if (listBlogEntity.isEmpty()) {
                binding.rvTask.visibility = View.GONE
                binding.txtListEmpty.visibility = View.VISIBLE
            } else {
                binding.rvTask.visibility = View.VISIBLE
                binding.txtListEmpty.visibility = View.GONE
                mAdapter!!.notifyDataSetChanged()
            }
        }

        binding.edSearch.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                mAdapter!!.filter.filter(newText)
                return false
            }

        })
    }

    private fun isNetworkAvailable(context: Context) =
        (context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager).run {
            getNetworkCapabilities(activeNetwork)?.run {
                hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR)
                        || hasTransport(NetworkCapabilities.TRANSPORT_WIFI)
                        || hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET)
            } ?: false
        }

    private fun showErrorRed() {
        SweetAlertDialog(this, SweetAlertDialog.ERROR_TYPE)
            .setTitleText("Oops...")
            .setContentText("Sin conexión a internet!")
            .show()
    }
    override fun onBackPressed() {
    }

    override fun onItemClick(item: Blog) {
        val intent = Intent(this, ShowDetailActivity::class.java)
        intent.putExtra("EXTRA_BLOG_CONTENT", item.content)
        intent.putExtra("EXTRA_BLOG_TITLE", item.title)
        intent.putExtra("EXTRA_BLOG_AUTHOR", item.author)
        startActivity(intent)
    }
}