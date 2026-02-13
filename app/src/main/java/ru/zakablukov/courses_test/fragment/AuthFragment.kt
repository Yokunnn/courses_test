package ru.zakablukov.courses_test.fragment

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.net.toUri
import androidx.navigation.fragment.findNavController
import dev.androidbroadcast.vbpd.viewBinding
import org.koin.androidx.viewmodel.ext.android.viewModel
import ru.zakablukov.courses_test.R
import ru.zakablukov.courses_test.databinding.FragmentAuthBinding
import ru.zakablukov.courses_test.viewmodel.AuthViewModel

class AuthFragment : Fragment() {

    private val binding by viewBinding(FragmentAuthBinding::bind)
    private val viewModel: AuthViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_auth, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initAuthButton()
        initExternalButtons()
    }

    private fun initAuthButton() {
        with(binding.authButton) {
            setOnClickListener {
                if (viewModel.validFields(
                        binding.emailEditText.text.toString(),
                        binding.passwordEditText.text.toString()
                    )
                ) {
                    findNavController().navigate(R.id.action_authFragment_to_mainFragment)
                }
            }
        }
    }

    private fun initExternalButtons() {
        binding.vkButton.setOnClickListener {
            startActivity(Intent(Intent.ACTION_VIEW, URL_VK.toUri()))
        }
        binding.okButton.setOnClickListener {
            startActivity(Intent(Intent.ACTION_VIEW, URL_OK.toUri()))
        }
    }

    companion object {
        private const val URL_VK = "https://vk.com/"
        private const val URL_OK = "https://ok.ru/"
    }
}