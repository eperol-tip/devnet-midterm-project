package com.example.devnet_mid_project

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.example.devnet_mid_project.databinding.FragmentLoginBinding

class LoginFragment : Fragment() {
    lateinit var binding: FragmentLoginBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentLoginBinding.inflate(inflater, container, false)

        binding.btnLogin.setOnClickListener{ view: View ->
            val user = binding.username.text.toString()
            val pass = binding.password.text.toString()
            if ((user == "group10") && (pass == "123456"))
            {
                view.findNavController().navigate(R.id.homeFragment)
            }
            else
            {
                val toast = Toast.makeText(context, "WRONG USERNAME OR PASSWORD!", Toast.LENGTH_SHORT)
                toast.show()
            }
        }
        binding.btnRegister.setOnClickListener { gotoRegistration() }

        return binding.root
    }
    private fun gotoRegistration() {
        findNavController().navigate(R.id.action_loginFragment_to_registrationFragment)
    }
}