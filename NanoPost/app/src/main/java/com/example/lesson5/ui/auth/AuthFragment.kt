package com.example.lesson5.ui.auth

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.lesson5.R
import com.example.lesson5.data.model.CheckUsernameResult
import com.example.lesson5.databinding.FragmentAuthBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AuthFragment : Fragment() {

    private lateinit var binding: FragmentAuthBinding
    private val authViewModel: AuthViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAuthBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.continueButton.setOnClickListener {
            authViewModel.authLogic(
                username = binding.editText.text.toString(),
                password = binding.editTextPass.text.toString()
            )
        }

        authViewModel.authLiveData.observe(viewLifecycleOwner) {
            when (it) {
                CheckUsernameResult.TooShort -> binding.til.error =
                    getString(R.string.error_tooShort)
                CheckUsernameResult.TooLong -> binding.til.error = getString(R.string.error_tooLong)
                CheckUsernameResult.InvalidCharacters -> binding.til.error =
                    getString(R.string.error_invalidCharacters)
                CheckUsernameResult.Taken -> {
                    binding.til.error = null
                    binding.til.isEnabled = false
                    binding.tilPass.isVisible = true
                }
                CheckUsernameResult.Free -> {
                    binding.til.error = null
                    binding.til.isEnabled = false
                    binding.tilPass.isVisible = true

                }
            }
        }

        authViewModel.navLiveData.observe(viewLifecycleOwner) {
            findNavController().navigate(
                AuthFragmentDirections.actionAuthFragmentToProfileFragment2()
            )
        }
    }
}