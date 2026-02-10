package ru.zakablukov.courses_test.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import dev.androidbroadcast.vbpd.viewBinding
import ru.zakablukov.courses_test.R
import ru.zakablukov.courses_test.databinding.FragmentAuthBinding

class AuthFragment : Fragment() {

    private val binding by viewBinding(FragmentAuthBinding::bind)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_auth, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initAuthButton()
    }

    private fun initAuthButton() {
        binding.authButton.setOnClickListener {
            findNavController().navigate(R.id.action_authFragment_to_mainFragment)
        }
    }
}