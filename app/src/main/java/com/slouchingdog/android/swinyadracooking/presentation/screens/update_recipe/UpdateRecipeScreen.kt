package com.slouchingdog.android.swinyadracooking.presentation.screens.update_recipe

import android.Manifest
import android.net.Uri
import android.os.Environment
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.core.content.FileProvider
import androidx.hilt.navigation.compose.hiltViewModel
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.isGranted
import com.google.accompanist.permissions.rememberPermissionState
import com.slouchingdog.android.swinyadracooking.R
import com.slouchingdog.android.swinyadracooking.presentation.GradientButton
import com.slouchingdog.android.swinyadracooking.presentation.screens.update_recipe.components.CookingStepsItem
import com.slouchingdog.android.swinyadracooking.presentation.screens.update_recipe.components.DishDescriptionFields
import com.slouchingdog.android.swinyadracooking.presentation.screens.update_recipe.components.ImagePicker
import com.slouchingdog.android.swinyadracooking.presentation.screens.update_recipe.components.ImageSourceDialog
import com.slouchingdog.android.swinyadracooking.presentation.screens.update_recipe.components.IngredientItem
import java.io.File
import java.io.IOException

@OptIn(ExperimentalMaterial3Api::class, ExperimentalPermissionsApi::class)
@Composable
fun UpdateRecipeScreen(id: String?, navigateBack: () -> Unit) {
    val context = LocalContext.current
    val updateRecipeViewModel =
        hiltViewModel<UpdateRecipeViewModel, UpdateRecipeViewModel.UpdateRecipeViewModelFactory> {
            it.create(id)
        }
    val state: UpdateRecipeScreenState by updateRecipeViewModel.updateRecipeScreenState.collectAsState()
    val focusManager = LocalFocusManager.current
    val singlePhotoPickerLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.PickVisualMedia(),
        onResult = { uri -> uri?.let { updateRecipeViewModel.onImageUriChange(it) } }
    )

    //Блок камеры
    val cameraPermissionState = rememberPermissionState(Manifest.permission.CAMERA)
    var currentPhotoUri by remember { mutableStateOf<Uri?>(null) }

    val cameraLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.TakePicture()
    ) { success ->
        if (success) {
            updateRecipeViewModel.onImageUriChange(currentPhotoUri)
        }
    }

    // Проверяем разрешения перед запуском камеры
    LaunchedEffect(Unit) {
        if (!cameraPermissionState.status.isGranted) {
            cameraPermissionState.launchPermissionRequest()
        }
    }

    fun createNewPhotoUri(): Uri {
        val timestamp = System.currentTimeMillis()
        val fileName = "IMG_${timestamp}.jpg"

        val file = File(
            context.getExternalFilesDir(Environment.DIRECTORY_PICTURES),
            fileName
        )

        return FileProvider.getUriForFile(
            context,
            "${context.packageName}.provider",
            file
        )
    }

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        text = if (id == null) stringResource(R.string.add_recipe_screen_title) else state.dishName,
                        style = MaterialTheme.typography.titleLarge
                    )
                },
                navigationIcon = {
                    IconButton(onClick = { navigateBack() }) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Default.ArrowBack,
                            contentDescription = "Back"
                        )
                    }
                },
                scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior(rememberTopAppBarState()),
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors()
            )
        }, containerColor = MaterialTheme.colorScheme.surface
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(PaddingValues(top = innerPadding.calculateTopPadding()))
                .clip(RoundedCornerShape(24.dp))
                .background(color = MaterialTheme.colorScheme.background)
                .padding(8.dp)
                .clickable(
                    interactionSource = null,
                    indication = null,
                    onClick = { focusManager.clearFocus() })
        ) {
            if (state.imageSourceSelectionOpened) {
                ImageSourceDialog(
                    onDismissRequest = { updateRecipeViewModel.onImageSourceDialogClose() },
                    onGalleryButtonClick = {
                        singlePhotoPickerLauncher.launch(
                            PickVisualMediaRequest(
                                ActivityResultContracts.PickVisualMedia.ImageOnly
                            )
                        )
                    },
                    onCameraButtonClick = {
                        if (cameraPermissionState.status.isGranted) {
                            val newUri = createNewPhotoUri()
                            currentPhotoUri = newUri
                            cameraLauncher.launch(newUri)
                        }
                    }
                )
            }

            LazyColumn(
                modifier = Modifier.padding(top = 24.dp, bottom = 8.dp, start = 8.dp, end = 8.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                item {
                    DishDescriptionFields(updateRecipeViewModel)
                }

                item {
                    Text(
                        stringResource(R.string.dish_photo_title),
                        style = MaterialTheme.typography.titleMedium
                    )
                    ImagePicker(
                        modifier = Modifier
                            .padding(16.dp),
                        imageUri = state.imageUri,
                        onImageClick = {
                            updateRecipeViewModel.onImageSourceDialogOpen()
                        }
                    )
                }

                item {
                    Text(
                        text = stringResource(R.string.ingredients_title),
                        style = MaterialTheme.typography.titleMedium
                    )
                }

                item {
                    Column(
                        modifier = Modifier
                            .border(
                                width = 2.dp,
                                color = MaterialTheme.colorScheme.outline,
                                shape = RoundedCornerShape(12.dp)
                            )
                            .padding(all = 16.dp)
                            .fillMaxWidth(),
                        verticalArrangement = Arrangement.spacedBy(16.dp)
                    ) {
                        state.ingredients.forEachIndexed { index, ingredient ->
                            IngredientItem(
                                ingredient = ingredient,
                                ingredientIndex = index,
                                ingredientsCount = state.ingredients.size,
                                isExpanded = ingredient.isUnitTypeExpanded,
                                onNameChange = { index, name ->
                                    updateRecipeViewModel.onIngredientNameChange(
                                        index, name
                                    )
                                },
                                onAmountChange = { index, amount ->
                                    updateRecipeViewModel.onIngredientAmountChange(index, amount)
                                },
                                onUnitTypeChange = { index, unitType ->
                                    updateRecipeViewModel.onIngredientUnitTypeChange(
                                        index,
                                        unitType
                                    )
                                },
                                onIngredientDelete = { updateRecipeViewModel.onIngredientRemove(it) },
                                onUnitTypeExpandedChange = {
                                    updateRecipeViewModel.onIngredientUnitTypeExpandedChange(
                                        index
                                    )
                                },
                                onDismissRequest = { })
                        }

                        TextButton(
                            onClick = { updateRecipeViewModel.onIngredientAdd() },
                            contentPadding = PaddingValues(0.dp)
                        ) {
                            Icon(
                                modifier = Modifier.padding(end = 8.dp),
                                imageVector = Icons.Default.AddCircle,
                                contentDescription = "Add ingredient"
                            )
                            Text(stringResource(R.string.add_ingredient_button_text))
                        }
                    }

                }

                item {
                    Text(
                        text = stringResource(R.string.cooking_steps_title),
                        style = MaterialTheme.typography.titleMedium
                    )
                }

                item {
                    Column(
                        modifier = Modifier
                            .border(
                                2.dp,
                                MaterialTheme.colorScheme.outline,
                                RoundedCornerShape(12.dp)
                            )
                            .padding(16.dp)
                            .fillMaxWidth(),
                        verticalArrangement = Arrangement.spacedBy(16.dp)
                    ) {
                        state.cookingSteps.forEachIndexed { index, cookingStep ->
                            CookingStepsItem(
                                stepDescription = cookingStep.description,
                                stepIndex = index + 1,
                                stepsCount = state.cookingSteps.size,
                                onStepChange = { updateRecipeViewModel.onStepUpdate(index, it) },
                                onDeleteStep = { updateRecipeViewModel.onStepRemove(index) })
                        }
                        TextButton(
                            onClick = { updateRecipeViewModel.onStepAdd() },
                            contentPadding = PaddingValues(0.dp)
                        ) {
                            Icon(
                                Icons.Default.AddCircle,
                                contentDescription = "Add cooking step",
                                modifier = Modifier.padding(end = 8.dp)
                            )
                            Text(stringResource(R.string.add_step_button_text))
                        }
                    }
                }

                item {
                    GradientButton(
                        modifier = Modifier
                            .fillMaxWidth(),
                        onClick = {
                            updateRecipeViewModel.onSaveButtonClick { navigateBack() }
                        })
                    {
                        Text(stringResource(R.string.save_button_text))
                    }
                }
            }
        }
    }
}