package com.jl.blog.activities

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowInsets
import android.view.WindowInsetsController
import android.view.WindowManager
import cn.pedant.SweetAlert.SweetAlertDialog
import com.jl.blog.databinding.ActivityRegisterBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RegisterActivity : AppCompatActivity() {

    lateinit var binding: ActivityRegisterBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)
        @Suppress("DEPRECATION") if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
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
        listeners()
    }

    private fun listeners() {
        binding.btnRegister.setOnClickListener {
            if (binding.edTitle.text.isEmpty()) {
                showError(1)
                return@setOnClickListener
            }
            if (binding.edAuthor.text.isEmpty()) {
                showError(2)
                return@setOnClickListener
            }
            if (binding.edContent.text.isEmpty()) {
                showError(3)
                return@setOnClickListener
            }
        }

        binding.txtReturn.setOnClickListener {
            finish()
        }
    }

    private fun showError(error: Int) {
        when (error) {
            1 -> {
                SweetAlertDialog(this, SweetAlertDialog.ERROR_TYPE).setTitleText("Oops...")
                    .setContentText("Campo Titulo requerido").show()
                binding.edTitle.error = "requerido"
                binding.edAuthor.error = null
                binding.edContent.error = null
            }
            2 -> {
                SweetAlertDialog(this, SweetAlertDialog.ERROR_TYPE).setTitleText("Oops...")
                    .setContentText("Campo Autor requerido").show()
                binding.edAuthor.error = "requerido"
                binding.edTitle.error = null
                binding.edContent.error = null
            }
            3 -> {
                SweetAlertDialog(this, SweetAlertDialog.ERROR_TYPE).setTitleText("Oops...")
                    .setContentText("Campo Contenido requerido").show()
                binding.edContent.error = "requerido"
                binding.edTitle.error = null
                binding.edContent.error = null
            }
        }
    }
}