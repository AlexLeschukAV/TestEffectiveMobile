package com.example.favourites.fragment

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
    private lateinit var bottomNavigationDataObserver: Observer<NavigationData>

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

private fun getVacancyWord(n: Int): String {
    return when (n) {
        0 -> "вакансий"
        1 -> "вакансия"
        2, 3, 4 -> "вакансии"
        else -> "вакансий"
    }
}

