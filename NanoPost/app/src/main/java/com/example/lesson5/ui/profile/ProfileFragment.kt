package com.example.lesson5.ui.profile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.ConcatAdapter
import com.example.lesson5.databinding.FragmentProfileBinding
import com.example.lesson5.ui.common.ImageCardAdapter
import com.example.lesson5.ui.common.PostAdapter
import com.example.lesson5.ui.common.ProfileAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ProfileFragment : Fragment() {

    private var binding: FragmentProfileBinding? = null
    private val profileAdapter: ProfileAdapter = ProfileAdapter()
    private val postAdapter: PostAdapter = PostAdapter()
    private val imageCardAdapter: ImageCardAdapter = ImageCardAdapter()
    private val profileViewModel: ProfileViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentProfileBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        profileViewModel.getProfile()
        profileViewModel.getPosts()

        binding?.apply {

            fab.setOnClickListener {
                findNavController().navigate(
                    ProfileFragmentDirections.actionProfileFragment2ToPostCreateFragment()
                )
            }

            recycler.adapter = ConcatAdapter(
                profileAdapter, imageCardAdapter, postAdapter
            )
            postAdapter.onItemClicked = {
                findNavController().navigate(
                    ProfileFragmentDirections.actionProfileFragment2ToFragmentPost2(it)
                )
            }

            profileViewModel.profileLiveData.observe(viewLifecycleOwner) {
                profileAdapter.submitList(listOf(it))
                imageCardAdapter.submitList(listOf(it.image))
            }

            profileViewModel.postLiveData.observe(viewLifecycleOwner) {
                postAdapter.submitData(viewLifecycleOwner.lifecycle, it)
            }


            imageCardAdapter.onItemClicked = {
                findNavController().navigate(
                    ProfileFragmentDirections.actionProfileFragment2ToImagesFragment(
                        profileViewModel.profileLiveData.value!!.id
                    )
                )
            }
        }
    }
}