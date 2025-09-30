//package com.slouchingdog.android.swinyadracooking.presentation.screens.recipe_list
//
//import androidx.compose.foundation.layout.Arrangement
//import androidx.compose.foundation.layout.padding
//import androidx.compose.foundation.lazy.grid.GridCells
//import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
//import androidx.compose.foundation.lazy.grid.items
//import androidx.compose.material.icons.Icons
//import androidx.compose.material.icons.filled.Add
//import androidx.compose.material3.CenterAlignedTopAppBar
//import androidx.compose.material3.ExperimentalMaterial3Api
//import androidx.compose.material3.FloatingActionButton
//import androidx.compose.material3.Icon
//import androidx.compose.material3.Scaffold
//import androidx.compose.material3.Text
//import androidx.compose.runtime.Composable
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.res.stringResource
//import androidx.compose.ui.tooling.preview.Preview
//import androidx.compose.ui.unit.dp
//import com.slouchingdog.android.swinyadracooking.R
//import com.slouchingdog.android.swinyadracooking.domain.entities.RecipeDetailedEntity
//
//@OptIn(ExperimentalMaterial3Api::class)
//@Composable
//@Preview
//fun RecipeListScreen() {
//    Scaffold(
//        topBar = {
//            CenterAlignedTopAppBar(
//                title = { Text(stringResource(R.string.recipe_list_screen_title)) })
//        },
//        floatingActionButton = {
//            FloatingActionButton(onClick = {}) {
//                Icon(
//                    imageVector = Icons.Default.Add,
//                    contentDescription = stringResource(R.string.add_recipe_button_description)
//                )
//            }
//        }
//    ) { innerPadding ->
//        LazyVerticalGrid(
//            columns = GridCells.Fixed(2),
//            modifier = Modifier
//                .padding(innerPadding)
//                .padding(16.dp),
//            horizontalArrangement = Arrangement.spacedBy(16.dp),
//            verticalArrangement = Arrangement.spacedBy(16.dp)
//        ) {
//            items(testList) { recipe ->
//                RecipeCard(recipe)
//            }
//        }
//    }
//}