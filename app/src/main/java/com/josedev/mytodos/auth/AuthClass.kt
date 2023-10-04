package com.josedev.mytodos.auth

import android.content.Context
import androidx.biometric.BiometricManager
import androidx.biometric.BiometricPrompt
import androidx.core.content.ContextCompat
import androidx.fragment.app.FragmentActivity

class AuthClass {

    private var canAuthenticate = false
    // Because this var is defined in execution time, is used the lateinit
    private lateinit var promptInfo: BiometricPrompt.PromptInfo
    private lateinit var context: Context
    private lateinit var activity: FragmentActivity


    fun setupAuth(context: Context, activity: FragmentActivity) {
        this.context = context
        if(BiometricManager.from(activity).canAuthenticate(
                BiometricManager.Authenticators.BIOMETRIC_STRONG or
                        BiometricManager.Authenticators.DEVICE_CREDENTIAL)
            == BiometricManager.BIOMETRIC_SUCCESS)
        {
            canAuthenticate = true
            promptInfo = BiometricPrompt.PromptInfo.Builder()
                .setTitle("Biometrical Auth")
                .setSubtitle("Auth using your sensor")
                .setAllowedAuthenticators(
                    BiometricManager.Authenticators.BIOMETRIC_STRONG or
                            BiometricManager.Authenticators.DEVICE_CREDENTIAL)
                .build()
        }
    }

    fun authenticate(auth: (auth:Boolean) -> Unit){
        if(canAuthenticate){
            BiometricPrompt(activity, ContextCompat.getMainExecutor(activity),
                object : BiometricPrompt.AuthenticationCallback(){
                    override fun onAuthenticationSucceeded(result: BiometricPrompt.AuthenticationResult) {
                        super.onAuthenticationSucceeded(result)
                        auth(true)
                    }
                }).authenticate(promptInfo)
        }else{
            auth(true)
        }
    }
}
