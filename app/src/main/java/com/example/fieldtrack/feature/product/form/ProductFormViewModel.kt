package com.example.fieldtrack.feature.product.form

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.toRoute
import com.example.fieldtrack.R
import com.example.fieldtrack.data.db.entity.ProductEntity
import com.example.fieldtrack.data.repository.ProductRepository
import com.example.fieldtrack.navigation.Routes
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProductFormViewModel @Inject constructor(
    private val productRepository: ProductRepository,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val productId: Long = savedStateHandle.toRoute<Routes.ProductForm>().productId ?: -1L

    var uiState by mutableStateOf(ProductUiState(isEditing = productId != -1L))
        private set

    init {
        if (productId != -1L) {
            loadProduct(productId)
        }
    }

    private fun loadProduct(id: Long) {
        viewModelScope.launch {
            productRepository.getProductById(id)?.let { product ->
                uiState = uiState.copy(
                    id = product.id,
                    name = product.name,
                    category = product.category ?: "",
                    defaultReapplyDays = product.defaultReapplyDays?.toString() ?: "",
                    storageLocation = product.storageLocation ?: "",
                    notes = product.notes ?: ""
                )
            }
        }
    }

    fun onEvent(event: ProductEvent) {
        when (event) {
            is ProductEvent.NameChanged -> {
                uiState = uiState.copy(name = event.name, nameErrorRes = null)
            }
            is ProductEvent.CategoryChanged -> {
                uiState = uiState.copy(category = event.category)
            }
            is ProductEvent.DefaultReapplyDaysChanged -> {
                uiState = uiState.copy(defaultReapplyDays = event.days)
            }
            is ProductEvent.StorageLocationChanged -> {
                uiState = uiState.copy(storageLocation = event.location)
            }
            is ProductEvent.NotesChanged -> {
                uiState = uiState.copy(notes = event.notes)
            }
            ProductEvent.SaveClicked -> {
                saveProduct()
            }
        }
    }

    private fun saveProduct() {
        if (uiState.name.isBlank()) {
            uiState = uiState.copy(nameErrorRes = R.string.error_product_name_required)
            return
        }

        viewModelScope.launch {
            uiState = uiState.copy(isSaving = true)
            val product = ProductEntity(
                id = if (productId != -1L) productId else 0L,
                name = uiState.name,
                normalizedName = uiState.name.lowercase().trim(),
                category = uiState.category.ifBlank { null },
                defaultReapplyDays = uiState.defaultReapplyDays.toIntOrNull(),
                storageLocation = uiState.storageLocation.ifBlank { null },
                notes = uiState.notes.ifBlank { null }
            )
            
            productRepository.saveProduct(product)
            
            uiState = uiState.copy(isSaving = false, isSaveSuccess = true)
        }
    }
}