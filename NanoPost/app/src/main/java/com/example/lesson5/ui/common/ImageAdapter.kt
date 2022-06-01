package com.example.lesson5.ui.common

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.lesson5.databinding.ViewImageBinding
import com.example.lesson5.model.Image

class ImageAdapter : PagingDataAdapter<Image, ImageAdapter.ImageViewHolder>(ImageItemCallback) {

    var onItemClicked: ((id: String) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return ImageViewHolder(
            ViewImageBinding.inflate(inflater, parent, false)
        )

    }

    override fun onBindViewHolder(holder: ImageViewHolder, position: Int) {
        getItem(position)?.let {
            holder.bind(it)
        }
    }

    inner class ImageViewHolder(

        private val binding: ViewImageBinding

    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(item: Image) {
            binding.imageInGrid.load(item.sizes.first().url)
            binding.root.setOnClickListener {
                onItemClicked?.invoke(item.id)
            }
        }
    }

    private object ImageItemCallback : DiffUtil.ItemCallback<Image>() {

        override fun areItemsTheSame(oldItem: Image, newItem: Image): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Image, newItem: Image): Boolean {
            return oldItem == newItem
        }
    }
}