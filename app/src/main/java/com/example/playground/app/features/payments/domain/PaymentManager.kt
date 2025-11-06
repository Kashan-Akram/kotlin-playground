package com.example.playground.app.features.payments.domain

import com.example.playground.app.features.payments.core.models.PaymentResult
import javax.inject.Inject

interface PaymentManager {

    fun pay(provider : String, amount : Double, currency : String) : PaymentResult

    fun verify(provider: String, id : String) : PaymentResult

}

class PaymentManagerImpl @Inject constructor(
    val providers : Map<String, PaymentProviderUseCase>
) : PaymentManager {

    override fun pay(provider: String, amount: Double, currency: String) : PaymentResult {
        val useCase = providers[provider]
        return useCase?.pay?.invoke(amount, currency) ?: PaymentResult()
    }

    override fun verify(provider: String, id: String) : PaymentResult {
        val useCase = providers[provider]
        return useCase?.verify?.invoke(id) ?: PaymentResult()
    }

}