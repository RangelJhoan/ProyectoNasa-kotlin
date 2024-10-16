package com.rangeljhoandev.proyectonasa.ui.view

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.rangeljhoandev.proyectonasa.R
import com.rangeljhoandev.proyectonasa.data.model.PostImageModel
import com.rangeljhoandev.proyectonasa.databinding.ActivityMainBinding
import com.rangeljhoandev.proyectonasa.ui.adapter.LastFiveTodayImagesAdapter
import com.rangeljhoandev.proyectonasa.ui.viewmodel.PostImageViewModel
import com.rangeljhoandev.proyectonasa.util.Util
import java.util.Calendar
import java.util.Date

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: LastFiveTodayImagesAdapter
    private val postImageViewModel: PostImageViewModel by viewModels()

    private lateinit var postImage: PostImageModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        enableEdgeToEdge()
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(binding.main) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(48, systemBars.top, 48, systemBars.bottom)
            insets
        }

        val dateFiveDaysBeforeCurrentDate = Util.modifyDate(Date(), Calendar.DAY_OF_YEAR, -5)
        val dateFiveDaysBeforeCurrentDateFormatted =
            Util.formatDate(dateFiveDaysBeforeCurrentDate, "yyyy-MM-dd")

        val dateOneDayBeforeCurrentDate = Util.modifyDate(Date(), Calendar.DAY_OF_YEAR, -1)
        val dateOneDayBeforeCurrentDateFormatted =
            Util.formatDate(dateOneDayBeforeCurrentDate, "yyyy-MM-dd")

        postImageViewModel.onCreate(
            dateFiveDaysBeforeCurrentDateFormatted,
            dateOneDayBeforeCurrentDateFormatted
        )

        setupObservers()
        setupOnClickListeners()
    }

    private fun setupOnClickListeners() {
        binding.btnView.setOnClickListener {
            Intent(this, DetailActivity::class.java).apply {
                putExtra("post_image", postImage)
                startActivity(this)
            }
        }
    }

    private fun setupObservers() {
        postImageViewModel.postImage.observe(this) { todayImage ->
            postImage = todayImage

            Glide.with(this)
                .load(todayImage.hdurl)
                .error(R.drawable.nasa_logo)
                .into(binding.ivTodayImage)

            binding.tvTitle.text = todayImage.title
            binding.tvDate.text = todayImage.date
        }

        postImageViewModel.lastFiveTodayImages.observe(this) { todayImages ->
            adapter = LastFiveTodayImagesAdapter(this, todayImages)
            binding.rvLastFiveTodayImages.layoutManager = LinearLayoutManager(this)
            binding.rvLastFiveTodayImages.adapter = adapter
        }
    }
}