package com.example.fieldtrack.feature.product.form

sealed interface ProductEffect {
    data object NavigateBack : ProductEffect
}