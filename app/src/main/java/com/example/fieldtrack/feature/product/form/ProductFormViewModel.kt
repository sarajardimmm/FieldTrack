package com.example.fieldtrack.feature.product.form

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.toRoute
import com.example.fieldtrack.R
import com.example.fieldtrack.data.db.entity.ProductEntity
import com.example.fieldtrack.data.repository.ProductRepository
import com.example.fieldtrack.navigation.Routes
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProductFormViewModel @Inject constructor(
    private val productRepository: ProductRepository,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val productId: Long = savedStateHandle.toRoute<Routes.ProductForm>().productId ?: -1L

    private val _uiState = MutableStateFlow(ProductUiState(isEditing = productId != -1L))
    val uiState: StateFlow<ProductUiState> = _uiState.asStateFlow()

    private val _effect = MutableSharedFlow<ProductEffect>()
    val effect = _effect.asSharedFlow()

    init {
        if (productId != -1L) {
            loadProduct(productId)
        }
    }

    private fun loadProduct(id: Long) {
        viewModelScope.launch {
            productRepository.getProductById(id)?.let { product ->
                _uiState.update { it.copy(
                    id = product.id,
                    name = product.name,
                    category = product.category ?: "",
                    defaultReapplyDays = product.defaultReapplyDays?.toString() ?: "",
                    storageLocation = product.storageLocation ?: "",
                    notes = product.notes ?: ""
                ) }
            }
        }
    }

    fun onEvent(event: ProductEvent) {
        when (event) {
            is ProductEvent.NameChanged -> {
                _uiState.update { it.copy(name = event.name, nameErrorRes = null) }
            }
            is ProductEvent.CategoryChanged -> {
                _uiState.update { it.copy(category = event.category) }
            }
            is ProductEvent.DefaultReapplyDaysChanged -> {
                _uiState.update { it.copy(defaultReapplyDays = event.days) }
            }
            is ProductEvent.StorageLocationChanged -> {
                _uiState.update { it.copy(storageLocation = event.location) }
            }
            is ProductEvent.NotesChanged -> {
                _uiState.update { it.copy(notes = event.notes) }
            }
            ProductEvent.SaveClicked -> {
                saveProduct()
            }
        }
    }

    private fun saveProduct() {
        if (_uiState.value.name.isBlank()) {
            _uiState.update { it.copy(nameErrorRes = R.string.error_product_name_required) }
            return
        }

        viewModelScope.launch {
            // Check for duplicate name if creating new product
            if (productId == -1L && productRepository.isProductNameTaken(_uiState.value.name)) {
                _uiState.update { it.copy(nameErrorRes = R.string.error_product_already_exists) }
                return@launch
            }

            _uiState.update { it.copy(isSaving = true) }
            val product = ProductEntity(
                id = if (productId != -1L) productId else 0L,
                name = _uiState.value.name,
                normalizedName = _uiState.value.name.lowercase().trim(),
                category = _uiState.value.category.ifBlank { null },
                defaultReapplyDays = _uiState.value.defaultReapplyDays.toIntOrNull(),
                storageLocation = _uiState.value.storageLocation.ifBlank { null },
                notes = _uiState.value.notes.ifBlank { null }
            )
            
            productRepository.saveProduct(product)
            
            _uiState.update { it.copy(isSaving = false) }
            _effect.emit(ProductEffect.NavigateBack)
        }
    }
}