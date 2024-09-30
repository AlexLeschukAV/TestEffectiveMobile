package com.example.find_offers

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.find_offers.databinding.FragmentFindOffersBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FindOffersFragment : Fragment(R.layout.fragment_find_offers) {
    private lateinit var binding: FragmentFindOffersBinding
    private val viewModel: FindOffersViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentFindOffersBinding.bind(view)
    }

}