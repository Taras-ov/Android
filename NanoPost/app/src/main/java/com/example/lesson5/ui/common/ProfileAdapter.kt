package com.example.lesson5.ui.common

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.lesson5.databinding.ProfileCardBinding
import com.example.lesson5.model.Profile

class ProfileAdapter : ListAdapter<Profile, ProfileAdapter.ProfileViewHolder>(ProfileItemCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProfileViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return ProfileViewHolder(
            ProfileCardBinding.inflate(inflater, parent, false)
        )

    }

    override fun onBindViewHolder(holder: ProfileViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class ProfileViewHolder(
        private val binding: ProfileCardBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(item: Profile) {
            binding.cardTitle.text = item.displayName ?: item.username
            binding.cardSubtitle.text = item.bio
            binding.profileIcon.load(item.avatarSmall)
            binding.photosNum.text = item.imagesCount.toString()
            binding.postsNum.text = item.postsCount.toString()
            binding.subsNum.text = item.subscribersCount.toString()
        }
    }

    private object ProfileItemCallback : DiffUtil.ItemCallback<Profile>() {

        override fun areItemsTheSame(oldItem: Profile, newItem: Profile): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Profile, newItem: Profile): Boolean {
            return oldItem == newItem
        }
    }
}