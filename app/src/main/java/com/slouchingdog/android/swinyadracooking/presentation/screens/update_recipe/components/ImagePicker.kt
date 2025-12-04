package com.slouchingdog.android.swinyadracooking.presentation.screens.update_recipe.components

import android.net.Uri
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddAPhoto
import androidx.compose.material.icons.filled.CameraAlt
import androidx.compose.material.icons.filled.PhotoLibrary
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import coil.compose.AsyncImage
import com.slouchingdog.android.swinyadracooking.R

@Composable
fun ImagePicker(modifier: Modifier, imageUri: Uri? = null, onImageClick: () -> Unit) {
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
                contentDescription = "Add photo placeholder",
                tint = MaterialTheme.colorScheme.onPrimaryContainer
            )
        }
    }
}

@Composable
fun ImageSourceDialog(
    onDismissRequest: () -> Unit,
    onGalleryButtonClick: () -> Unit,
    onCameraButtonClick: () -> Unit
) {
    Dialog(onDismissRequest = { onDismissRequest() }) {
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
                    text = "Источник изображения",
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
                            onCameraButtonClick()
                            onDismissRequest()
                        },
                        colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.onPrimaryContainer)
                    ) {
                        Icon(Icons.Default.CameraAlt, contentDescription = "Camera")
                        Spacer(Modifier.width(8.dp))
                        Text("Камера")
                    }
                    Button(
                        onClick = {
                            onGalleryButtonClick()
                            onDismissRequest()
                        },
                        colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.onPrimaryContainer)
                    ) {
                        Icon(Icons.Default.PhotoLibrary, contentDescription = "Camera")
                        Spacer(Modifier.width(8.dp))
                        Text("Галерея")
                    }
                }
            }
        }
    }
}