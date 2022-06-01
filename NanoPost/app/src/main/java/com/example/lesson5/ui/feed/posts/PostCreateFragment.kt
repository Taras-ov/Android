package com.example.lesson5.ui.feed.posts

import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.lesson5.PostCreateService
import com.example.lesson5.R
import com.example.lesson5.databinding.FragmentPostCreateBinding
import com.example.lesson5.ui.common.PostCreateAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PostCreateFragment : Fragment() {

    private lateinit var binding: FragmentPostCreateBinding
    private val postCreateViewModel: PostCreateViewModel by viewModels()
    private val postCreateAdapter = PostCreateAdapter()
    private lateinit var getContent: ActivityResultLauncher<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        getContent = registerForActivityResult(ActivityResultContracts.GetContent()) { uri: Uri? ->
            uri?.let {
                postCreateViewModel.onImageAdded(it)
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentPostCreateBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.apply {
            recycler.adapter = postCreateAdapter

            toolBar.setOnMenuItemClickListener {
                when (it.itemId) {
                    R.id.check -> {
                        val intent = PostCreateService.newIntent(
                            requireContext(),
                            binding.editText.text.toString(),
                            postCreateViewModel.postCreateLiveData.value.orEmpty()
                        )
                        requireContext().startService(intent)
                        findNavController().popBackStack()
                        true
                    }
                    else -> false
                }
            }

            toolBar.setNavigationOnClickListener {
                findNavController().popBackStack()
            }

            addImageChip.setOnClickListener {
                getContent.launch(
                    "image/*"
                )
            }

            postCreateViewModel.postCreateLiveData.observe(viewLifecycleOwner) {
                postCreateAdapter.submitList(it)
            }
        }

    }
}