package com.tailwagging.breedsguide

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.tailwagging.breedsguide.ui.screens.BreedDescriptionScreen
import com.tailwagging.breedsguide.ui.screens.DogAgeScreen
import com.tailwagging.breedsguide.ui.screens.MenuScreen
import com.tailwagging.breedsguide.ui.screens.TopBreedsScreen
import com.tailwagging.breedsguide.ui.theme.TailWaggingBreedsGuideTheme
import com.tailwagging.breedsguide.viewmodels.BreedVM

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            val breedVM: BreedVM by viewModels()

            TailWaggingBreedsGuideTheme {
                val navController = rememberNavController()
                NavHost(navController = navController, startDestination = "MenuScreen") {
                    composable("MenuScreen") {
                        MenuScreen(navigation = navController)
                    }
                    composable("TopBreedsScreen") {
                        TopBreedsScreen(navigation = navController, vm = breedVM)
                    }
                    composable("BreedDescriptionScreen") {
                        BreedDescriptionScreen(navigation = navController, vm = breedVM)
                    }
                    composable("DogAgeScreen") {
                        DogAgeScreen(navigation = navController)
                    }
                }
            }
        }
    }
}