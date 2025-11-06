package com.example.playground.app.features.payments.domain

import com.example.playground.app.features.payments.core.models.PaymentResult
import javax.inject.Inject

class PaymentProviderUseCase @Inject constructor(
    val pay : PayUseCase,
    val verify : VerifyUseCase
)

interface PayUseCase {

    operator fun invoke(amount : Double, currency : String) : PaymentResult

}

interface VerifyUseCase {

    operator fun invoke(id : String) : PaymentResult

}