package com.example.devnet_mid_project

import android.os.Bundle
import android.view.*
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import com.example.devnet_mid_project.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val binding = DataBindingUtil.inflate<FragmentHomeBinding>(inflater,
            R.layout.fragment_home, container, false)

        binding.btnSubmit.setOnClickListener{ view: View ->

            when (binding.userOptions.checkedRadioButtonId) {
                R.id.option_objective -> view.findNavController()
                    .navigate(R.id.objectiveFragment)
                R.id.option_description -> view.findNavController()
                    .navigate(R.id.descriptionFragment)
                else -> view.findNavController()
                    .navigate(R.id.contactFragment)
            }
        }
        setHasOptionsMenu(true)
        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.options_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return NavigationUI.onNavDestinationSelected(item,requireView().findNavController())
                || super.onOptionsItemSelected(item)

    }

}