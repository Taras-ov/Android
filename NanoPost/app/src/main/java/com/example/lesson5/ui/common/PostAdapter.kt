package com.example.lesson5.ui.common

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.lesson5.model.Post
import com.example.lesson5.databinding.ViewContentCardBinding

@SuppressLint("SimpleDateFormat")
class PostAdapter : PagingDataAdapter<Post, PostAdapter.PostViewHolder>(PostItemCallback) {

    var onItemClicked: ((id: String) -> Unit)? = null
    val sdf = java.text.SimpleDateFormat("MMM-d-yyyy HH:mm:ss")

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return PostViewHolder(
            ViewContentCardBinding.inflate(inflater, parent, false)
        )

    }

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        val item = getItem(position)
        if (item != null) {
            holder.bind(item)
        }
    }

    inner class PostViewHolder(
        private val binding: ViewContentCardBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(item: Post) {
            binding.cardSubtitle.text = sdf.format(item.dateCreated)
            binding.cardTitle.text = item.owner.displayName ?: item.owner.username
            binding.profileIcon.load(item.owner.avatarUrl)
            binding.cardText.isVisible = !item.text.isNullOrBlank()
            binding.cardText.text = item.text
            binding.cardImage.load(item.images.firstOrNull()?.sizes?.first()?.url)
            binding.root.setOnClickListener {
                onItemClicked?.invoke(item.id)
            }
        }
    }

    private object PostItemCallback : DiffUtil.ItemCallback<Post>() {

        override fun areItemsTheSame(oldItem: Post, newItem: Post): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Post, newItem: Post): Boolean {
            return oldItem == newItem
        }
    }
}