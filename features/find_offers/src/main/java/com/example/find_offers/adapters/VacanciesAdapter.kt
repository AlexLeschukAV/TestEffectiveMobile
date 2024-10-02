package com.example.find_offers.adapters

import android.annotation.SuppressLint
import android.os.Build
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.base.R as OffersR
import com.example.base.databinding.ItemVacanciesBinding
import com.example.models.Vacancy
import java.time.LocalDate
import java.time.format.DateTimeFormatter

@RequiresApi(Build.VERSION_CODES.O)
class VacanciesAdapter(
    private val onClickLike: (String) -> Unit,
    private val onClickVacancy: (Vacancy) -> Unit,
) :
    ListAdapter<Vacancy, VacanciesAdapter.VacanciesViewHolder>(DiffCallback()) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VacanciesViewHolder {
        val binding =
            ItemVacanciesBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return VacanciesViewHolder(binding, onClickLike, onClickVacancy)
    }

    override fun onBindViewHolder(holder: VacanciesViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class VacanciesViewHolder(
        private val binding: ItemVacanciesBinding,
        private val onClickLike: (String) -> Unit,
        private val onClickVacancy: (Vacancy) -> Unit,
    ) : RecyclerView.ViewHolder(binding.root) {
        @SuppressLint("SetTextI18n")
        fun bind(item: Vacancy) {
            with(binding) {
                item.lookingNumber?.let {
                    textLooking.text = "Сейчас просматривает $it ${getPersonWord(it)} "
                }
                icon.setImageDrawable(
                    if (item.isFavorite)
                        ContextCompat.getDrawable(itemView.context, OffersR.drawable.ic_like_icon)
                    else
                        ContextCompat.getDrawable(itemView.context, OffersR.drawable.ic_dislike_icon)
                )
                title.text = item.title
                textTown.text = item.address.town
                textCompany.text = item.company
                textExperience.text = item.experience.previewText
                textDayPublished.text = "Опубликовано ${formatDateString(item.publishedDate)}"
                binding.icon.setOnClickListener {
                    onClickLike(item.id)
                }
                binding.root.setOnClickListener {
                    onClickVacancy(item)
                }
            }
        }

        private fun getPersonWord(n: Int): String {
            val namber = (Math.round((n * 10).toDouble()) % 10).toInt()
            return when {
                namber == 2 || namber == 3 || namber == 4 -> "человека"
                else -> "человек"
            }
        }


        private fun formatDateString(input: String): String {
            val date = LocalDate.parse(input, DateTimeFormatter.ofPattern("yyyy-MM-dd"))
            return date.format(DateTimeFormatter.ofPattern("d MMMM"))
        }
    }

    class DiffCallback : DiffUtil.ItemCallback<Vacancy>() {
        override fun areItemsTheSame(oldItem: Vacancy, newItem: Vacancy): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Vacancy, newItem: Vacancy): Boolean {
            return oldItem == newItem
        }

    }
}

