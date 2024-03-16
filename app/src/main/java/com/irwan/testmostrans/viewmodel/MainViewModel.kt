package com.irwan.testmostrans.viewmodel

import android.content.ContentValues
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.irwan.testmostrans.api.ApiConfig
import com.irwan.testmostrans.api.CharResponse
import com.irwan.testmostrans.api.ResultsItem
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainViewModel : ViewModel(){

    private val _loadChar = MutableLiveData<List<ResultsItem>>()
    val loadChar: LiveData<List<ResultsItem>> = _loadChar

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    fun loadCharacter() {
        _isLoading.value = true
        val client = ApiConfig.getApiService().getCharacter()
        client.enqueue(object : Callback<CharResponse> {
            override fun onResponse(
                call: Call<CharResponse>,
                response: Response<CharResponse>
            ) {
                _isLoading.value = false
                if (response.isSuccessful) {
                    val responseBody = response.body()
                    responseBody?.let { charResponse ->
                        _loadChar.value = charResponse.results
                    }
                } else {
                    Log.e(ContentValues.TAG, "onFailure: ${response.message()}")
                }
            }
            override fun onFailure(call: Call<CharResponse>, t: Throwable) {
                _isLoading.value = false
                Log.e(ContentValues.TAG, "onFailure: ${t.message}")
            }
        })
    }
}