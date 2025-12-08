package com.slouchingdog.android.swinyadracooking.presentation.screens.update_recipe.components

import android.Manifest
import android.net.Uri
import android.os.Environment
import androidx.activity.compose.ManagedActivityResultLauncher
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddAPhoto
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.core.content.FileProvider
import coil.compose.AsyncImage
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.isGranted
import com.google.accompanist.permissions.rememberPermissionState
import com.slouchingdog.android.swinyadracooking.R
import com.slouchingdog.android.swinyadracooking.presentation.screens.update_recipe.UpdateRecipeViewModel
import java.io.File

@Composable
fun ImagePicker(modifier: Modifier = Modifier, imageUri: Uri? = null, onImageClick: () -> Unit) {
    Column(verticalArrangement = Arrangement.spacedBy(16.dp)) {
        Text(
            stringResource(R.string.dish_photo_title),
            style = MaterialTheme.typography.titleMedium
        )
        Box(
            modifier = modifier
                .width(100.dp)
                .height(100.dp)
                .clip(RoundedCornerShape(12.dp))
                .border(2.dp, MaterialTheme.colorScheme.outline, RoundedCornerShape(12.dp))
                .background(MaterialTheme.colorScheme.primaryContainer)
                .clickable { onImageClick() },
            contentAlignment = Alignment.Center
        ) {
            if (imageUri != null) {
                AsyncImage(
                    model = imageUri,
                    contentDescription = stringResource(R.string.image_preview),
                    modifier = Modifier.fillMaxSize(),
                    contentScale = ContentScale.Crop
                )
            } else {
                Icon(
                    imageVector = Icons.Default.AddAPhoto,
                    contentDescription = stringResource(R.string.add_photo_placeholder_descr),
                    tint = MaterialTheme.colorScheme.onPrimaryContainer
                )
            }
        }
    }
}

@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun ImageSourceDialog(
    updateRecipeViewModel: UpdateRecipeViewModel,
    singlePhotoPickerLauncher: ManagedActivityResultLauncher<PickVisualMediaRequest, Uri?>,
    cameraLauncher: ManagedActivityResultLauncher<Uri, Boolean>
) {
    val context = LocalContext.current

    //Блок камеры
    val cameraPermissionState = rememberPermissionState(Manifest.permission.CAMERA)
    var currentPhotoUri by remember { mutableStateOf<Uri?>(null) }


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
    Dialog(onDismissRequest = { updateRecipeViewModel.onImageSourceDialogClose() }) {
        Box(
            modifier = Modifier
                .size(300.dp, 150.dp)
                .clip(RoundedCornerShape(24.dp))
                .background(MaterialTheme.colorScheme.primaryContainer)

        ) {
            Column(modifier = Modifier.fillMaxSize()) {
                Text(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 16.dp),
                    text = stringResource(R.string.image_source_dialog_title),
                    style = MaterialTheme.typography.titleMedium,
                    textAlign = TextAlign.Center
                )
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(32.dp),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Button(
                        onClick = {
                            if (cameraPermissionState.status.isGranted) {
                                updateRecipeViewModel.onImageUriChange(createNewPhotoUri())
                                cameraLauncher.launch(updateRecipeViewModel.updateRecipeScreenState.value.imageUri!!)
                            }
                            updateRecipeViewModel.onImageSourceDialogClose()
                        },
                        colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.onPrimaryContainer)
                    ) {
                        Text(stringResource(R.string.camera_button_text))
                    }
                    Button(
                        onClick = {
                            singlePhotoPickerLauncher.launch(
                                PickVisualMediaRequest(
                                    ActivityResultContracts.PickVisualMedia.ImageOnly
                                )
                            )
                            updateRecipeViewModel.onImageSourceDialogClose()
                        },
                        colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.onPrimaryContainer)
                    ) {
                        Text(stringResource(R.string.gallery_button_text))
                    }
                }
            }
        }
    }
}