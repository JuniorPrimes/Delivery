package com.junior.delivery.signin.presentation

import android.util.Log
import android.util.Patterns
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.junior.delivery.signin.domain.SignInUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignInViewModel @Inject constructor(
    private val signInUseCase: SignInUseCase
) : ViewModel() {
    private val _emailOrPhone = MutableLiveData<String>()
    val emailOrPhone: LiveData<String> = _emailOrPhone

    private val _password = MutableLiveData<String>()
    val password: LiveData<String> = _password

    private val _isSignInButtonEnable = MutableLiveData<Boolean>()
    val isSignInButtonEnable: LiveData<Boolean> = _isSignInButtonEnable

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    fun onSignInChanged(emailOrPhone: String, password: String) {
        _emailOrPhone.value = emailOrPhone
        _password.value = password
        _isSignInButtonEnable.value = enableLogin()
    }

    private fun enableLogin() : Boolean =
        (Patterns.PHONE.matcher(_emailOrPhone.value!!).matches()
                || Patterns.EMAIL_ADDRESS.matcher(_emailOrPhone.value!!).matches())
                && passwordMeetsTheRules()

    private fun passwordMeetsTheRules() : Boolean = _password.value!!.length > 8
            && _password.value!!.contains(Regex("[A-Z]"))
            && _password.value!!.contains(Regex("[a-z]"))
            && _password.value!!.contains(Regex("[0-9]"))

    fun onSignInButtonCLicked() {
        viewModelScope.launch {
            _isLoading.value = true
            val result = signInUseCase(_emailOrPhone.value!!, _password.value!!)

            if(result) {
                Log.i("COACH", "Navigate to Home")
            }
            _isLoading.value = false
        }
    }
}