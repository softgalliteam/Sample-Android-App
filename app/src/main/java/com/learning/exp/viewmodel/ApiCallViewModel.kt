package com.learning.exp.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.learning.exp.model.ApiCalRepository
import com.learning.exp.model.dataclasses.ResponseData
import com.learning.exp.model.dataclasses.ResponseDataItem
import com.learning.exp.utils.Constants.TAG
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


sealed class ApiCallState {
    object Loading : ApiCallState()
    data class Success(val articles: List<ResponseDataItem>) : ApiCallState()
    data class Error(val message: String) : ApiCallState()
}


class ApiCallViewModel : ViewModel() {
    private val _screenState = MutableLiveData<ApiCallState>()
    val screenState: MutableLiveData<ApiCallState>
        get() = _screenState

    init {
        getComputerList()
    }

    private val repository = ApiCalRepository()
    private fun getComputerList() {
        viewModelScope.launch(Dispatchers.IO) {
            _screenState.postValue(ApiCallState.Loading)
            try {
                val response = repository.getComputerListRetrofit() // Recommended way to make network request using Retrofit
                //val response = repository.getComputerListOkHttp()

                Log.d(TAG, "Response from repository: $response")
                val computerList: ResponseData = response
                delay(3000)
                _screenState.postValue(ApiCallState.Success(computerList))

            } catch (e: Exception) {
                _screenState.postValue(ApiCallState.Error("Error fetching articles: ${e.message}"))
            }
        }
    }

}