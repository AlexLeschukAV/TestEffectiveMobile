package com.example.favourites.fragment

import android.annotation.SuppressLint
import android.os.Build
import android.os.Bundle
import android.view.View
import androidx.annotation.RequiresApi
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.base.BaseFragment
import com.example.common.adapters.VacanciesAdapter
import com.example.common.ui.RespondDialog.Companion.showRespondDialog
import com.example.favourites.contract.FavoriteVacancyContract.Action
import com.example.favourites.contract.FavoriteVacancyContract.Effect
import com.example.favourites.contract.FavoriteVacancyContract.State
import com.example.favourites.databinding.FragmentFavouritesBinding
import com.example.favourites.viewmodel.FavoriteVacancyViewModel
import dagger.hilt.android.AndroidEntryPoint

@RequiresApi(Build.VERSION_CODES.O)
@AndroidEntryPoint
class FavouritesFragment : BaseFragment<FragmentFavouritesBinding, State, Action, Effect>() {
    private val viewmodel: FavoriteVacancyViewModel by viewModels()

    private val adapter: VacanciesAdapter by lazy { createVacancyAdapter() }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.rwVacancies.adapter = adapter
    }

    override fun observers() {
        collectFlow(viewmodel.state) { render(it) }
        collectFlow(viewmodel.effect) { render(it) }
    }

    override fun render(effect: Effect) {}

    @SuppressLint("SetTextI18n")
    override fun render(state: State) {
        with(state) {
            vacancy?.let {
                binding.sizeVacancy.text = "${it.size} ${getVacancyWord(it.size)} "
                val list = it.filter { vacancy -> vacancy.isFavorite }
                adapter.submitList(list)
                val numberBadge = it.count { vacancy -> vacancy.isFavorite }
                createBadge(numberBadge)
            }
        }
    }

    private fun createVacancyAdapter(): VacanciesAdapter {
        return VacanciesAdapter(
            onClickLike = { viewmodel.setAction(Action.OnClickLike(it)) },
            onClickVacancy = {
                findNavController().navigate(
                    FavouritesFragmentDirections.actionFavouritesFragmentToViewVacancyFragment(
                        it
                    )
                )
            },
            onClickButton = {
                showRespondDialog(it) {}
            }
        )
    }
}

private fun getVacancyWord(n: Int): String {
    return when (n) {
        0 -> "вакансий"
        1 -> "вакансия"
        2, 3, 4 -> "вакансии"
        else -> "вакансий"
    }
}

