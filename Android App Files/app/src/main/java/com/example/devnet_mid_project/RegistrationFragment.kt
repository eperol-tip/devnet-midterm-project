package com.example.devnet_mid_project

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.devnet_mid_project.databinding.FragmentRegistrationBinding

class RegistrationFragment : Fragment() {
    lateinit var binding: FragmentRegistrationBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentRegistrationBinding.inflate(inflater, container, false)
        binding.btnReg.setOnClickListener{
            gotoLogin()
        }

        return binding.root
    }
    private fun gotoLogin() {
        findNavController().navigate(R.id.action_registrationFragment_to_loginFragment)
    }

}