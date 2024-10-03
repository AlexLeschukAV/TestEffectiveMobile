package com.example.favourites.fragment

import android.os.Build
import android.os.Bundle
import android.view.View
import androidx.annotation.RequiresApi
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.base.BaseFragment
import com.example.base.adapters.VacanciesAdapter
import com.example.base.ui.RespondDialog.Companion.showRespondDialog
import com.example.favourites.databinding.FragmentFavouritesBinding
import dagger.hilt.android.AndroidEntryPoint
import com.example.favourites.contract.FavoriteVacancyContract.State
import com.example.favourites.contract.FavoriteVacancyContract.Action
import com.example.favourites.contract.FavoriteVacancyContract.Effect
import com.example.favourites.viewmodel.FavoriteVacancyViewModel

@RequiresApi(Build.VERSION_CODES.O)
@AndroidEntryPoint
class FavouritesFragment : BaseFragment<FragmentFavouritesBinding, State, Action, Effect>() {
    private val viewmodel: FavoriteVacancyViewModel by viewModels()
    private val adapter: VacanciesAdapter by lazy {
        VacanciesAdapter(
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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.rwVacancies.adapter = adapter
    }

    override fun observers() {
        collectFlow(viewmodel.state) { render(it) }
        collectFlow(viewmodel.effect) { render(it) }
    }

    override fun render(effect: Effect) {}

    override fun render(state: State) {
        with(state) {
            vacancy?.let {
                val list = it.filter { vacancy -> vacancy.isFavorite }
                adapter.submitList(list)
            }
        }
    }
}