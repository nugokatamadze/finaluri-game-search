package com.example.gamessearchapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.example.gamessearchapp.databinding.FragmentRegistrationnBinding
import com.google.firebase.auth.FirebaseAuth

class RegistrationnFragment : BaseFragment<FragmentRegistrationnBinding>(FragmentRegistrationnBinding::inflate) {


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        onClickListeners()
        registerActivityListeners()
    }

    private fun onClickListeners() {
        binding.back.setOnClickListener {
            findNavController().navigate(RegistrationnFragmentDirections.actionRegistrationnFragmentToWelcomeFragment())
        }
        binding.register2.setOnClickListener {
            findNavController().navigate(RegistrationnFragmentDirections.actionRegistrationnFragmentToSearchFragment())
        }
    }

    private fun registerActivityListeners() {
        binding.register2.setOnClickListener {
            val email = binding.email2.text.toString()
            val pass = binding.pass2.text.toString()

            if (email.isEmpty() || pass.isEmpty()) {
                Toast.makeText(requireContext(), "ველების შევსება სავალდებულოა", Toast.LENGTH_SHORT)
                    .show()
                return@setOnClickListener
            }
            FirebaseAuth.getInstance().createUserWithEmailAndPassword(email, pass)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        findNavController().navigate(RegistrationnFragmentDirections.actionRegistrationnFragmentToSearchFragment())
                    } else {
                        Toast.makeText(requireContext(), "მონაცემები არასწორია", Toast.LENGTH_SHORT)
                            .show()
                    }
                }
        }
    }
}