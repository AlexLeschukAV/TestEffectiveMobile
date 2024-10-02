package com.example.find_offers.fragment

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.base.R
import com.example.base.databinding.FragmentViewVacancyBinding
import com.example.models.Vacancy
import com.google.android.material.chip.Chip
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ViewVacancyFragment : Fragment(R.layout.fragment_view_vacancy) {
    private lateinit var binding: FragmentViewVacancyBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentViewVacancyBinding.bind(view)
        val vacancy = arguments?.getParcelable<Vacancy>("vacancy")
        vacancy?.let { bindVacancy(it) }
        binding.topMaterialTB.setNavigationOnClickListener { findNavController().popBackStack() }
    }

    private fun bindVacancy(item: Vacancy) = with(binding) {
        if (item.isFavorite)
            topMaterialTB.menu?.findItem(R.id.like)?.icon =
                ContextCompat.getDrawable(requireContext(), R.drawable.ic_like_icon)
        else
            topMaterialTB.menu?.findItem(R.id.like)?.icon =
                ContextCompat.getDrawable(requireContext(), R.drawable.ic_dislike_icon)
        title.text = item.title
        company.text = item.company
        salaryFull.text = item.salary.full
        experience.text = item.experience.previewText
        schedules.text = item.schedules.joinToString(separator = ", ")
        item.appliedNumber?.let {
            cardResponse.title.text = "${it} человек уже откликнулись"
        } ?: let {
            cardResponse.title.text = "Нет данных о количестве откликов"
        }
        item.lookingNumber?.let {
            cardViewed.title.text = "${it} ${getPersonWord(it)} сейчас смотрят"
        } ?: let {
            cardViewed.title.text = "Нет данных о количестве просмотров"
        }
        cardViewed.icon.setImageDrawable(
            ContextCompat.getDrawable(
                requireContext(),
                R.drawable.ic_view_now
            )
        )
        description.text = item.description
        responsibilities.text = item.responsibilities
        item.questions.forEach { question ->
            val chip = createChip(requireContext())
            chip.text = question
            chipGroupCompletedSmall.addView(chip)
        }
    }

    private fun createChip(context: Context): Chip {
        return Chip(context).apply {
            chipBackgroundColor =
                ContextCompat.getColorStateList(context, R.color.chips_background_color)
            chipStrokeWidth = 0f
            setTextColor(resources.getColor(R.color.white))
        }
    }

    private fun getPersonWord(n: Int): String {
        val namber = (Math.round((n * 10).toDouble()) % 10).toInt()
        return when {
            namber == 2 || namber == 3 || namber == 4 -> "человека"
            else -> "человек"
        }
    }
}