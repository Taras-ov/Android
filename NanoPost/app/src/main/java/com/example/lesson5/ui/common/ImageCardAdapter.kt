package com.example.lesson5.ui.common

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.lesson5.databinding.ImageCardBinding
import com.example.lesson5.model.Image

class ImageCardAdapter :
    ListAdapter<List<Image>, ImageCardAdapter.ImageCardViewHolder>(ImageItemCallback) {

    var onItemClicked: (() -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageCardViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return ImageCardViewHolder(
            ImageCardBinding.inflate(inflater, parent, false)
        )

    }

    override fun onBindViewHolder(holder: ImageCardViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class ImageCardViewHolder(
        private val binding: ImageCardBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        private val imageViews = listOf(
            binding.image1, binding.image2, binding.image3, binding.image4)

        fun bind(item: List<Image>) {
             item.forEachIndexed { index, image ->
                 imageViews[index].load(image.sizes.last().url)
             }
            binding.root.setOnClickListener {
                onItemClicked?.invoke()
            }
        }
    }

    private object ImageItemCallback : DiffUtil.ItemCallback<List<Image>>() {

        override fun areItemsTheSame(oldItem: List<Image>, newItem: List<Image>): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: List<Image>, newItem: List<Image>): Boolean {
            return oldItem == newItem
        }
    }
}