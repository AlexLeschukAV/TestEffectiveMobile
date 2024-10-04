package com.example.offers.fragment

import android.annotation.SuppressLint
import android.os.Build
import android.os.Bundle
import android.view.View
import androidx.annotation.RequiresApi
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.example.base.BaseFragment
import com.example.base.BottomNavigationViewSource
import com.example.base.adapters.VacanciesAdapter
import com.example.base.ui.RespondDialog.Companion.showRespondDialog
import com.example.base.utils.NavigationData
import com.example.find_offers.databinding.FragmentFindOffersBinding
import com.example.offers.adapter.OffersAdapter
import com.example.offers.contract.OffersContract.Action
import com.example.offers.contract.OffersContract.Effect
import com.example.offers.contract.OffersContract.State
import com.example.offers.viewmodel.FindOffersViewModel
import dagger.hilt.android.AndroidEntryPoint

@RequiresApi(Build.VERSION_CODES.O)
@AndroidEntryPoint
class FindOffersFragment : BaseFragment<FragmentFindOffersBinding, State, Action, Effect>() {

    private val viewModel: FindOffersViewModel by viewModels()
    private lateinit var bottomNavigationDataObserver: Observer<NavigationData>
    private val offersAdapter: OffersAdapter by lazy { OffersAdapter() }
    private val vacanciesAdapter: VacanciesAdapter by lazy {
        VacanciesAdapter(
            onClickLike = {
                viewModel.setAction(Action.OnClickLike(it))
            },
            onClickVacancy = {
                findNavController().navigate(
                    FindOffersFragmentDirections.actionFindOffersFragmentToViewVacancyFragment(it)
                )
            },
            onClickButton = {
                showRespondDialog(it) {}
            }
        )
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

    @SuppressLint("SetTextI18n")
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
            offers?.vacancies?.let {
                viewModel.setAction(Action.AddVacancies(it))
                val numberBadge = it.count { vacancy -> vacancy.isFavorite }
                createBadge(numberBadge)
            }

            binding.buttonAddVacancies.setOnClickListener {
                viewModel.setAction(Action.OnShowAllVacancy)
                with(binding) {
                    buttonAddVacancies.hide()
                    offersRecommendation.hide()
                    tvVacancy.hide()
                    showAllVacancies.show()
                    countVacancy.text = "${state.offers?.vacancies?.size} вакансий"
                }
            }
        }
    }

    private fun createBadge(n: Int) {
        bottomNavigationDataObserver = Observer { navData ->
            val menuItem = navData.menuItem
            val badge = navData.bottomNavigationView?.getOrCreateBadge(menuItem)
            if (n > 0) {
                badge?.isVisible = true
                badge?.number = n
            } else badge?.isVisible = false
        }
        BottomNavigationViewSource.instance.observe(
            viewLifecycleOwner,
            bottomNavigationDataObserver
        )
    }
}