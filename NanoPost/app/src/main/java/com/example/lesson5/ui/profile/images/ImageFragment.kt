package com.example.lesson5.ui.profile.images

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import coil.load
import com.example.lesson5.R
import com.example.lesson5.databinding.FragmentImageBinding
import dagger.hilt.android.AndroidEntryPoint
import java.text.SimpleDateFormat

@AndroidEntryPoint
class ImageFragment : Fragment() {

    private lateinit var binding: FragmentImageBinding
    private val imageViewModel: ImageViewModel by viewModels()
    @SuppressLint("SimpleDateFormat")
    private val sdf = SimpleDateFormat("MMM-d-yyyy HH:mm:ss")
    private val args: ImageFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentImageBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        imageViewModel.getImage(args.imageId)

        imageViewModel.imageLiveData.observe(viewLifecycleOwner) {
            binding.dateCreated.text = sdf.format(it.dateCreated)
            binding.image.load(it.sizes.first().url)
            binding.owner.text = it.owner.username
            binding.profileIcon.load(it.owner.avatarUrl)
        }

        imageViewModel.imageDeleteLiveData.observe(viewLifecycleOwner) {
            findNavController().popBackStack()
        }

        binding.apply {
            toolbar.setOnMenuItemClickListener {
                when (it.itemId) {
                    R.id.delete -> {
                        imageViewModel.deleteImage()
                        true
                    }
                    else -> false
                }
            }

            toolbar.setNavigationOnClickListener {
                findNavController().popBackStack()
            }
        }
    }
}