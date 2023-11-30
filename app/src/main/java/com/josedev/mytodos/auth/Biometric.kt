package com.josedev.mytodos.auth

import androidx.biometric.BiometricManager
import androidx.biometric.BiometricPrompt
import androidx.core.content.ContextCompat
import androidx.fragment.app.FragmentActivity

object Biometric {
    fun authenticate(
        activity: FragmentActivity,
        title: String,
        subTitle: String,
        description: String,
        onSuccess: (BiometricPrompt.AuthenticationResult) -> Unit,
        onFailed: () -> Unit,
        onError: () -> Unit
    ) {
        val executor = ContextCompat.getMainExecutor(activity)
        val biometricPrompt = BiometricPrompt(activity, executor,
            object: BiometricPrompt.AuthenticationCallback() {
                override fun onAuthenticationSucceeded(result: BiometricPrompt.AuthenticationResult) {
                    super.onAuthenticationSucceeded(result)
                    onSuccess(result)
                }
                override fun onAuthenticationFailed() {
                    super.onAuthenticationFailed()
                    onFailed()
                }

                override fun onAuthenticationError(errorCode: Int, errString: CharSequence) {
                    super.onAuthenticationError(errorCode, errString)
                    onError()
                }


            })

        val promptInfo: BiometricPrompt.PromptInfo = BiometricPrompt.PromptInfo.Builder()
            .setTitle(title)
            .setSubtitle(subTitle)
            .setDescription(description)
            .setAllowedAuthenticators(
                BiometricManager.Authenticators.BIOMETRIC_STRONG or
                        BiometricManager.Authenticators.DEVICE_CREDENTIAL)
            .build()
        biometricPrompt.authenticate(promptInfo)
    }
}