package edu.itvo.salesapp.domain.validation

import edu.itvo.salesapp.domain.model.Customer
import edu.itvo.salesapp.presentation.customer.ValidationResult
import kotlin.text.isBlank

class CustomerValidator {
    operator fun invoke(customer: Customer): ValidationResult =
        listOfNotNull(
            "Id required".takeIf { customer.id.isBlank() },
            "Name required".takeIf { customer.name.isBlank() },
            "Email required".takeIf { customer.email.isBlank() }
        ).firstOrNull()
            ?.let { ValidationResult.Error(it) }
            ?: ValidationResult.Success
}




