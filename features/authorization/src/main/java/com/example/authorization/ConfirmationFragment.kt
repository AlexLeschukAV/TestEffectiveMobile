package com.example.authorization

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.authorization.databinding.FragmentConfirmationBinding

class ConfirmationFragment : Fragment(R.layout.fragment_confirmation) {
 private lateinit var binding: FragmentConfirmationBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentConfirmationBinding.bind(view)
        binding.buttonNextFindOffers.setOnClickListener {
            findNavController().navigate(com.example.find_offers.R.id.nav_find_offers)
        }
    }
}