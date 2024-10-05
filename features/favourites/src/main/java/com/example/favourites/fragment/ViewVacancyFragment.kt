package com.example.favourites.fragment

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.common.R
import com.example.common.databinding.FragmentViewVacancyBinding
import com.example.common.ui.RespondDialog.Companion.showRespondDialog
import com.example.models.Vacancy

class ViewVacancyFragment : Fragment(R.layout.fragment_view_vacancy) {
    private lateinit var binding: FragmentViewVacancyBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentViewVacancyBinding.bind(view)
        val vacancy = arguments?.getParcelable<Vacancy>("vacancy")

        vacancy?.let { com.example.common.utils.bindVacancy(requireContext(), binding, it) }

        binding.topMaterialTB.setNavigationOnClickListener { findNavController().popBackStack() }

        binding.buttonCallback.setOnClickListener {
            vacancy?.title?.let { title ->
                showRespondDialog(title) {
                    findNavController().navigateUp()
                }
            }
        }
    }
}