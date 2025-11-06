package com.example.playground.app.features.payments.core

import com.example.playground.app.features.payments.domain.PaymentManager
import com.example.playground.app.features.payments.domain.PaymentManagerImpl
import com.example.playground.app.features.payments.domain.PaymentProviderUseCase
import com.example.playground.app.features.payments.domain.usecases.PalPayUseCase
import com.example.playground.app.features.payments.domain.usecases.PayPalVerifyUseCase
import com.example.playground.app.features.payments.domain.usecases.StripePayUseCase
import com.example.playground.app.features.payments.domain.usecases.StripeVerifyUseCase
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped
import dagger.multibindings.IntoMap
import dagger.multibindings.StringKey

@Module
@InstallIn(ViewModelComponent::class)
abstract class PaymentsBindingModule {

    @ViewModelScoped
    @Binds
    abstract fun bindsPaymentManager(
        paymentManagerImpl : PaymentManagerImpl
    ) : PaymentManager

}

@Module
@InstallIn(ViewModelComponent::class)
object PaymentsProvidingModule {

    @ViewModelScoped
    @Provides
    @IntoMap
    @StringKey("paypal")
    fun providePayPalProvider(
        pay: PalPayUseCase,
        verify: PayPalVerifyUseCase
    ): PaymentProviderUseCase {
        return PaymentProviderUseCase(pay, verify)
    }

    @ViewModelScoped
    @Provides
    @IntoMap
    @StringKey("stripe")
    fun provideStripeProvider(
        pay: StripePayUseCase,
        verify: StripeVerifyUseCase
    ): PaymentProviderUseCase {
        return PaymentProviderUseCase(pay, verify)
    }

}