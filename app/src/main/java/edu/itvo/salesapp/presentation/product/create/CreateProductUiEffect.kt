package edu.itvo.salesapp.presentation.product.create

import android.os.Message

sealed interface CreateProductUiEffect {
    object NavigateBack: CreateProductUiEffect

    data class ShowError(val message: String): CreateProductUiEffect
    data class ShowSuccess(val message: String): CreateProductUiEffect
}