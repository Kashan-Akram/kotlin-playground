package com.example.playground.app.features.payments.domain

import com.example.playground.app.features.payments.core.models.PaymentResult
import com.example.playground.app.features.payments.domain.usecases.PalPayUseCase
import com.example.playground.app.features.payments.domain.usecases.PayPalVerifyUseCase
import com.example.playground.app.features.payments.domain.usecases.StripePayUseCase
import com.example.playground.app.features.payments.domain.usecases.StripeVerifyUseCase
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

// for manual class object initialization
class PaymentStrategy {
    companion object {

        fun getPaymentStrategy(provider: String): PayUseCase {
            return when (provider) {
                "paypal" -> {
                    PalPayUseCase()
                }
                "stripe" -> {
                    StripePayUseCase()
                }
                else -> {
                    throw IllegalArgumentException("provider not found")
                }
            }
        }

        fun getVerifyStrategy(provider : String) : VerifyUseCase {
            return when (provider) {
                "paypal" -> {
                    PayPalVerifyUseCase()
                }
                "stripe" -> {
                    StripeVerifyUseCase()
                }
                else -> {
                    throw IllegalArgumentException("provider not found")
                }
            }
        }

    }
}