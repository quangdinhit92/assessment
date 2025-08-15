package com.dinh.feature.UiModel

import android.R
import com.dinh.domain.entities.CropEntity


data class UiModelCrop(
    val id: String,
    val imageUrl: String,
    val name: String,
    val isActive: Boolean = true
)

fun CropEntity.toUiModelCrop() = UiModelCrop(
    id = this.id,
    name = this.name,
    imageUrl = this.imageUrl,
    isActive = null != this.imageUrl
)


