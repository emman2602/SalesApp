package edu.itvo.salesapp.presentation.customer.create


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import edu.itvo.salesapp.domain.model.Customer
import edu.itvo.salesapp.domain.usecase.customer.CreateCustomerUseCase
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CreateCustomerViewModel @Inject constructor(
    private val createCustomerUseCase: CreateCustomerUseCase
): ViewModel() {
    private val _state = MutableStateFlow(CreateCustomerUiState())
    val state: StateFlow<CreateCustomerUiState> = _state

    private val _effect = Channel<CreateCustomerUiEffect>()
    val effect = _effect.receiveAsFlow()

    private fun updateState(update: CreateCustomerUiState.() -> CreateCustomerUiState){
        _state.update(update)
    }

    fun onEvent(event: CreateCustomerUiEvent){
        when (event){
            is CreateCustomerUiEvent.IdChanged ->
                updateState { copy(id =event.value) }
            is CreateCustomerUiEvent.NameChanged ->
                updateState { copy(name = event.value) }
            is CreateCustomerUiEvent.EmailChanged ->
                updateState { copy(email = event.value) }
            CreateCustomerUiEvent.SaveClicked ->
                saveCustomer()
        }
    }

    private fun saveCustomer(){
        val currentState = state.value

        if (currentState.id.isBlank()){
            sendEffect(CreateCustomerUiEffect.ShowError("Code required"))
            return
        }

        viewModelScope.launch {
            updateState { copy(isLoading = true) }

            try {
                val customer = Customer(
                    id = currentState.id,
                    name = currentState.name,
                    email = currentState.email
                )

                createCustomerUseCase(customer)

                sendEffect(CreateCustomerUiEffect.ShowSuccess("Customer created"))
                sendEffect(CreateCustomerUiEffect.NavigateBack)
            } catch (e: Exception){
                sendEffect(
                    CreateCustomerUiEffect.ShowError(e.message ?: "Unknown error")
                )
            }finally {
                updateState { copy(isLoading = true) }
            }
        }

    }


    private fun sendEffect(effect: CreateCustomerUiEffect) {
        viewModelScope.launch {
            _effect.send(effect)
        }
    }

}


