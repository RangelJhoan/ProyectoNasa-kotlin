package com.rangeljhoandev.proyectonasa.ui.view

import android.os.Build
import android.os.Bundle
import android.text.method.ScrollingMovementMethod
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.bumptech.glide.Glide
import com.rangeljhoandev.proyectonasa.R
import com.rangeljhoandev.proyectonasa.data.model.PostImageModel
import com.rangeljhoandev.proyectonasa.databinding.ActivityDetailBinding

class DetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)

        enableEdgeToEdge()
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(binding.main) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        binding.tvExplanation.movementMethod = ScrollingMovementMethod()

        val todayImage = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            intent.getParcelableExtra("post_image", PostImageModel::class.java)
        } else {
            @Suppress("DEPRECATION")
            intent.getParcelableExtra("post_image")
        }

        todayImage?.let {
            Glide.with(this)
                .load(todayImage.hdurl)
                .error(R.drawable.nasa_logo)
                .into(binding.ivTodayImage)

            binding.tvTitle.text = todayImage.title
            binding.tvDate.text = todayImage.date
            binding.tvExplanation.text = todayImage.explanation
        }
    }
}