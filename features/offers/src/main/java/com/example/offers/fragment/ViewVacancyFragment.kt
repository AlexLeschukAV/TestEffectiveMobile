package com.example.offers.fragment

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.base.databinding.FragmentViewVacancyBinding
import com.example.base.ui.RespondDialog.Companion.showRespondDialog
import com.example.base.utils.bindVacancy
import com.example.models.Vacancy
import dagger.hilt.android.AndroidEntryPoint
import com.example.base.R as OffersR

@AndroidEntryPoint
class ViewVacancyFragment : Fragment(OffersR.layout.fragment_view_vacancy) {
    private lateinit var binding: FragmentViewVacancyBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentViewVacancyBinding.bind(view)

        val vacancy = arguments?.getParcelable<Vacancy>("vacancy")
        vacancy?.let { bindVacancy(requireContext(), binding, it) }

        binding.topMaterialTB.setNavigationOnClickListener { findNavController().popBackStack() }
        binding.buttonCallback.setOnClickListener {
            vacancy?.title?.let { title -> showRespondDialog(title){
                findNavController().navigateUp()
            } }
        }
    }
}