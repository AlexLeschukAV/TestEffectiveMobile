package com.example.base.utils

import android.content.Context
import androidx.core.content.ContextCompat
import com.example.base.R
import com.example.base.databinding.FragmentViewVacancyBinding
import com.example.models.Vacancy
import com.google.android.material.chip.Chip

fun bindVacancy(context: Context, binding: FragmentViewVacancyBinding, item: Vacancy) =
    with(binding) {
        if (item.isFavorite)
            topMaterialTB.menu?.findItem(R.id.like)?.icon =
                ContextCompat.getDrawable(
                    context,
                    R.drawable.ic_like_icon
                )
        else
            topMaterialTB.menu?.findItem(R.id.like)?.icon =
                ContextCompat.getDrawable(
                    context,
                    R.drawable.ic_dislike_icon
                )
        title.text = item.title
        company.text = item.company
        salaryFull.text = item.salary.full
        experience.text = item.experience.previewText
        schedules.text = item.schedules.joinToString(separator = ", ")
        item.appliedNumber?.let {
            val textTitle = "$it человек уже откликнулись"
            cardResponse.title.text = textTitle
        } ?: let {
            cardResponse.title.text = "Нет данных об откликов"
        }
        item.lookingNumber?.let {
            val textLooking = "$it ${getPersonWord(it)} сейчас смотрят"
            cardViewed.title.text = textLooking
        } ?: let {
            cardViewed.title.text = "Нет данных о просмотрах"
        }
        cardViewed.icon.setImageDrawable(
            ContextCompat.getDrawable(
                context,
                R.drawable.ic_view_now
            )
        )
        description.text = item.description
        responsibilities.text = item.responsibilities
        item.questions.forEach { question ->
            val chip = createChip(context)
            chip.text = question
            chipGroupCompletedSmall.addView(chip)
        }
    }

private fun createChip(context: Context): Chip {
    return Chip(context).apply {
        chipBackgroundColor =
            ContextCompat.getColorStateList(context, R.color.chips_background_color)
        chipStrokeWidth = 0f
        setTextColor(ContextCompat.getColor(context, R.color.white))
    }
}

private fun getPersonWord(n: Int): String {
    val nam = (Math.round((n * 10).toDouble()) % 10).toInt()
    return when {
        nam == 2 || nam == 3 || nam == 4 -> "человека"
        else -> "человек"
    }
}