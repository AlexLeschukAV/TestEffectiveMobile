package com.example.testeffectivemobile

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import com.google.android.material.bottomnavigation.BottomNavigationView
import dagger.hilt.android.AndroidEntryPoint
import com.example.favourites.R as FavouritesR
import com.example.find_offers.R as FindR
import com.example.messages.R as MessagesR
import com.example.profile.R as ProfileR
import com.example.responses.R as ResponseR

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var navController: NavController
    private val fragmentListWhenShowMenu = listOf(
        FindR.id.findOffersFragment,
        FavouritesR.id.favouritesFragment,
        ResponseR.id.responsesFragment,
        MessagesR.id.messagesFragment,
        ProfileR.id.profileFragment,
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupBottomNavigationMenu()
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.fragmentContainerView)
        return navController.navigateUp() || super.onSupportNavigateUp()
    }

    private fun setupBottomNavigationMenu() {
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.fragmentContainerView) as NavHostFragment
        navController = navHostFragment.navController

        val bottomNavView = findViewById<BottomNavigationView>(R.id.bottom_view)

        NavigationUI.setupWithNavController(bottomNavView, navController)
        bottomNavView.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.findOffersFragment -> {
                    navController.navigate(FindR.id.nav_find_offers)
                    true
                }

                R.id.favouritesFragment -> {
                    navController.navigate(FavouritesR.id.nav_favourite)
                    true
                }

                R.id.responsesFragment -> {
                    navController.navigate(ResponseR.id.nav_responses_offers)
                    true
                }

                R.id.messagesFragment -> {
                    navController.navigate(MessagesR.id.nav_message_offers)
                    true
                }

                R.id.profileFragment -> {
                    navController.navigate(ProfileR.id.nav_profile_offers)
                    true
                }

                else -> false
            }
        }

        navController.addOnDestinationChangedListener { _, destination, _ ->
            when (destination.id) {
                in fragmentListWhenShowMenu -> bottomNavView.isVisible = true
                else -> bottomNavView.isVisible = false
            }
        }
    }
}