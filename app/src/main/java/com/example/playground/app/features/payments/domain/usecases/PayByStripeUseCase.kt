package com.example.playground.app.features.payments.domain.usecases

import com.example.playground.app.features.payments.core.models.PaymentResult
import com.example.playground.app.features.payments.domain.PayUseCase
import javax.inject.Inject

class StripePayUseCase @Inject constructor( ) : PayUseCase {

    override fun invoke(amount: Double, currency: String): PaymentResult {
        return PaymentResult("payment by stripe")
    }

}