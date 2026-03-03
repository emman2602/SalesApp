package edu.itvo.salesapp.presentation.product.create

sealed interface CreateProductUiEvent {
    data class CodeChanged( val value: String): CreateProductUiEvent
    data class NameChanged(val value: String): CreateProductUiEvent
    data class DescriptionChanged( val value: String): CreateProductUiEvent
    data class CategoryChanged(val value: String): CreateProductUiEvent
    data class PriceChanged(val value: String): CreateProductUiEvent
    data class StockChanged(val value: String): CreateProductUiEvent
    data class TaxableChanged(val value: Boolean): CreateProductUiEvent
    object SaveClicked: CreateProductUiEvent


}