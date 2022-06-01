package com.example.lesson5.ui.profile.images

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.lesson5.databinding.FragmentImagesBinding
import com.example.lesson5.ui.common.ImageAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ImagesFragment : Fragment() {

    private var binding: FragmentImagesBinding? = null
    private val imageAdapter = ImageAdapter()
    private val imagesViewModel: ImagesViewModel by viewModels()
    private val args: ImagesFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentImagesBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        imagesViewModel.getProfileImages(args.profileId)

        binding?.apply {

            recycler.adapter = imageAdapter

            toolbar.setNavigationOnClickListener {
                findNavController().popBackStack()
            }
        }

        imagesViewModel.profileImagesLiveData.observe(viewLifecycleOwner) {
            imageAdapter.submitData(viewLifecycleOwner.lifecycle, it)
        }

        imageAdapter.onItemClicked = {
            findNavController().navigate(
                ImagesFragmentDirections.actionImagesFragmentToImageFragment(it)
            )
        }
    }
}