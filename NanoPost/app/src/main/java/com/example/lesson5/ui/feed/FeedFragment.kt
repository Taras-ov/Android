package com.example.lesson5.ui.feed

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.lesson5.databinding.FragmentFeedBinding
import com.example.lesson5.ui.common.PostAdapter
import com.example.lesson5.ui.profile.ProfileViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FeedFragment : Fragment() {

    private var binding: FragmentFeedBinding? = null
    private val postAdapter = PostAdapter()
    private val profileViewModel: ProfileViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentFeedBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        profileViewModel.getPosts()

        binding?.apply {

            fab.setOnClickListener {
                findNavController().navigate(
                    FeedFragmentDirections.actionFeedFragment3ToPostCreateFragment()
                )
            }

            recycler.adapter = postAdapter

            postAdapter.onItemClicked = {
                findNavController().navigate(
                    FeedFragmentDirections.actionFeedFragment3ToFragmentPost2(it)
                )
            }

            profileViewModel.postLiveData.observe(viewLifecycleOwner) {
                postAdapter.submitData(viewLifecycleOwner.lifecycle, it)
            }
        }
    }
}