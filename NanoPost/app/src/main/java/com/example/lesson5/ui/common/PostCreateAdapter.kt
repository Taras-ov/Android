package com.example.lesson5.ui.common

import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.lesson5.databinding.AddImageCardBinding

class PostCreateAdapter :
    ListAdapter<Uri, PostCreateAdapter.PostCreateViewHolder>(UriItemCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostCreateViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return PostCreateViewHolder(
            AddImageCardBinding.inflate(inflater, parent, false)
        )
    }

    override fun onBindViewHolder(holder: PostCreateViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class PostCreateViewHolder(

        private val binding: AddImageCardBinding

    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(item: Uri) {
            binding.image.load(item)
        }
    }

    private object UriItemCallback : DiffUtil.ItemCallback<Uri>() {

        override fun areItemsTheSame(oldItem: Uri, newItem: Uri): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: Uri, newItem: Uri): Boolean {
            return oldItem == newItem
        }
    }
}
