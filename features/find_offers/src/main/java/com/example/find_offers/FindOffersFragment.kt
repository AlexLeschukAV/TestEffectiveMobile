package com.example.find_offers

import android.os.Build
import android.os.Bundle
import android.view.View
import androidx.annotation.RequiresApi
import androidx.fragment.app.viewModels
import com.example.base.BaseMviFragment
import com.example.find_offers.OffersContract.Action
import com.example.find_offers.OffersContract.Effect
import com.example.find_offers.OffersContract.State
import com.example.find_offers.adapters.OffersAdapter
import com.example.find_offers.adapters.VacanciesAdapter
import com.example.find_offers.databinding.FragmentFindOffersBinding
import dagger.hilt.android.AndroidEntryPoint


@RequiresApi(Build.VERSION_CODES.O)
@AndroidEntryPoint
class FindOffersFragment : BaseMviFragment<FragmentFindOffersBinding, State, Action, Effect>() {

    private val viewModel: FindOffersViewModel by viewModels()
    private val offersAdapter: OffersAdapter by lazy { OffersAdapter() }
    private val vacanciesAdapter: VacanciesAdapter by lazy {
        VacanciesAdapter {
            viewModel.setAction(Action.OnClickLike(it))
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.offersRecommendation.adapter = offersAdapter
        binding.rwVacancies.adapter = vacanciesAdapter
    }

    override fun observers() {
        collectFlow(viewModel.state) { render(it) }
        collectFlow(viewModel.effect) { render(it) }
    }

    override fun render(effect: Effect) {
        when (effect) {
            is Effect.ShowError -> {
                toast(effect.message)
            }
        }
    }

    override fun render(state: State) {
        with(state) {
            if (isLoading) binding.progressBar.show() else binding.progressBar.hide()
            offers?.let {
                offersAdapter.submitList(it.offers)
                if (it.showAllVacancy) {
                    vacanciesAdapter.submitList(it.vacancies)
                } else {
                    vacanciesAdapter.submitList(it.vacancies.take(3))
                    binding.buttonAddVacancies.show()
                    binding.buttonAddVacancies.text = "Ещё ${it.vacancies.size - 3} вакансий"
                }
            }

            binding.buttonAddVacancies.setOnClickListener {
                binding.buttonAddVacancies.hide()
                viewModel.setAction(Action.OnShowAllVacancy)
            }
        }
    }

    override fun onStop() {
        super.onStop()
        viewModel.setAction(Action.OnShowAllVacancy)
    }
}