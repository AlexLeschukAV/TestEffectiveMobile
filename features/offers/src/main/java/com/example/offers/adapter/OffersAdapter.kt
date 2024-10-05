package com.example.offers.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.common.R
import com.example.find_offers.databinding.ItemRecommendationBinding
import com.example.models.Offer
import com.example.models.Vacancies

class OffersAdapter(
    private val onClick: (String) -> Unit,
) : ListAdapter<Offer, OffersAdapter.OfferViewHolder>(DiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OfferViewHolder {
        val binding =
            ItemRecommendationBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return OfferViewHolder(binding, onClick)
    }

    override fun onBindViewHolder(holder: OfferViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class OfferViewHolder(
        private val binding: ItemRecommendationBinding,
        private val onclick: (String) -> Unit,
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Offer) {
            val colorFirst = ContextCompat.getColor(itemView.context, R.color.icon_first_color)
            val colorSecond = ContextCompat.getColor(itemView.context, R.color.icon_second_color)
            val nearVacancies =
                ContextCompat.getDrawable(itemView.context, R.drawable.ic_near_vacancies)
            val levelUpResume =
                ContextCompat.getDrawable(itemView.context, R.drawable.ic_level_up_resume)
            val temporaryJob =
                ContextCompat.getDrawable(itemView.context, R.drawable.ic_temporary_job)
            with(binding) {
                when (item.id) {
                    Vacancies.NEAR_VACANCIES.value -> {
                        icon.setImageDrawable(nearVacancies)
                        icon.setBackgroundColor(colorFirst)
                    }

                    Vacancies.LEVEL_UP_RESUME.value -> {
                        icon.setImageDrawable(levelUpResume)
                        icon.setBackgroundColor(colorSecond)
                    }

                    Vacancies.TEMPORARY_JOB.value -> {
                        icon.setImageDrawable(temporaryJob)
                        icon.setBackgroundColor(colorSecond)
                    }
                }
                title.text = item.title
                item.button?.text.let {
                    textButton.text = it
                }
                root.setOnClickListener {
                    item.link?.let(onclick)
                }
            }
        }
    }
}
private class DiffCallback : DiffUtil.ItemCallback<Offer>() {
    override fun areItemsTheSame(oldItem: Offer, newItem: Offer): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Offer, newItem: Offer): Boolean {
        return oldItem == newItem
    }

}