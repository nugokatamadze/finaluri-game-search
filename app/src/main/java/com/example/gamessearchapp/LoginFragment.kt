package com.example.gamessearchapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.example.gamessearchapp.databinding.FragmentLoginnBinding
import com.google.firebase.auth.FirebaseAuth

class LoginnFragment : BaseFragment<FragmentLoginnBinding>(FragmentLoginnBinding::inflate) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        onClickListeners()
        loginActivityListeners()
    }

    private fun onClickListeners() {
        binding.loginbtn.setOnClickListener {
            findNavController().navigate(LoginnFragmentDirections.actionLoginnFragmentToSearchFragment())
        }
        binding.regBut.setOnClickListener {
            findNavController().navigate(LoginnFragmentDirections.actionLoginnFragmentToRegistrationnFragment())
        }
    }

    private fun loginActivityListeners() {
        binding.loginbtn.setOnClickListener {
            val email = binding.username.text.toString()
            val pass = binding.password.text.toString()

            if (email.isEmpty() || pass.isEmpty()) {
                Toast.makeText(requireContext(), "ველების შევსება სავალდებულოა", Toast.LENGTH_SHORT)
                    .show()
                return@setOnClickListener
            }
            FirebaseAuth.getInstance().signInWithEmailAndPassword(email, pass)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        findNavController().navigate(LoginnFragmentDirections.actionLoginnFragmentToSearchFragment())
                    } else {
                        Toast.makeText(requireContext(), "მონაცემები არასწორია", Toast.LENGTH_SHORT)
                            .show()
                    }
                }
        }
    }
}