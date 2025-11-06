package com.example.playground.app.features.payments.domain.usecases

import com.example.playground.app.features.payments.core.models.PaymentResult
import com.example.playground.app.features.payments.domain.VerifyUseCase
import javax.inject.Inject

class PayPalVerifyUseCase @Inject constructor( ) : VerifyUseCase {

    override fun invoke(id : String): PaymentResult {
        return PaymentResult("verify by pay pal")
    }

}