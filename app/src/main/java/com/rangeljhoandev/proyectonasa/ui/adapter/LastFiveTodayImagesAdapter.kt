package com.rangeljhoandev.proyectonasa.ui.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.rangeljhoandev.proyectonasa.data.model.PostImageModel
import com.rangeljhoandev.proyectonasa.databinding.ItemLastFiveTodayImagesBinding
import com.rangeljhoandev.proyectonasa.ui.view.DetailActivity

class LastFiveTodayImagesAdapter(
    private val context: Context,
    private val lastFiveTodayImages: List<PostImageModel>
) :
    RecyclerView.Adapter<LastFiveTodayImagesAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemLastFiveTodayImagesBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int = lastFiveTodayImages.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(lastFiveTodayImages[position])
    }

    inner class ViewHolder(private val binding: ItemLastFiveTodayImagesBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(postImage: PostImageModel) {
            binding.tvTodayImageTitle.text = postImage.title
            binding.tvTodayImageDate.text = postImage.date

            binding.btnView.setOnClickListener {
                Intent(context, DetailActivity::class.java).apply {
                    putExtra("post_image", postImage)
                    context.startActivity(this)
                }
            }
        }

    }

}