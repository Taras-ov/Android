package com.example.lesson5.ui.feed.posts

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import coil.load
import com.example.lesson5.R
import com.example.lesson5.databinding.FragmentPostBinding
import dagger.hilt.android.AndroidEntryPoint
import java.text.SimpleDateFormat

/**
 * A simple [Fragment] subclass.
 * Use the [PostFragment.newInstance] factory method to
 * create an instance of this fragment.
 */

@AndroidEntryPoint
class PostFragment : Fragment() {

    private lateinit var binding: FragmentPostBinding
    private val args: PostFragmentArgs by navArgs()
    private val postViewModel: PostViewModel by viewModels()
    @SuppressLint("SimpleDateFormat")
    private val sdf = SimpleDateFormat("MMM-d-yyyy HH:mm:ss")

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentPostBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        postViewModel.postDeleteLiveData.observe(viewLifecycleOwner) {
            findNavController().popBackStack()
        }

        binding.apply {
            toolbar.setOnMenuItemClickListener {
                when (it.itemId) {
                    R.id.delete -> {
                        postViewModel.deletePost()
                        true
                    }
                    else -> false
                }
            }

            toolbar.setNavigationOnClickListener {
                findNavController().popBackStack()
            }

            owner.setOnClickListener {
                findNavController().navigate(
                    PostFragmentDirections.actionFragmentPost2ToProfileFragment2()
                )
            }

            profileIcon.setOnClickListener {
                findNavController().navigate(
                    PostFragmentDirections.actionFragmentPost2ToProfileFragment2()
                )
            }

            dateCreated.setOnClickListener {
                findNavController().navigate(
                    PostFragmentDirections.actionFragmentPost2ToProfileFragment2()
                )
            }
        }

        postViewModel.getPost(args.postId)

        postViewModel.postLiveData.observe(viewLifecycleOwner) {
            binding.dateCreated.text = sdf.format(it.dateCreated)
            binding.postText.text = it.text
            if (it.images.isNotEmpty()) binding.postImage.load(it.images.first().sizes.first().url)
            binding.owner.text = it.owner.username
            binding.profileIcon.load(it.owner.avatarUrl)
        }
    }
}