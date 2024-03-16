package com.irwan.testmostrans.viewmodel

import android.content.ContentValues
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.irwan.testmostrans.api.ApiConfig
import com.irwan.testmostrans.api.ResultsItem
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DetailViewModel : ViewModel(){

    private val _detail = MutableLiveData<ResultsItem>()
    val detailChar: LiveData<ResultsItem> = _detail

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    fun loadDetails(id: Int) {
        _isLoading.value = true
        val client = ApiConfig.getApiService().getDetail(id)
        client.enqueue(object : Callback<ResultsItem> {
            override fun onResponse(
                call: Call<ResultsItem>,
                response: Response<ResultsItem>
            ) {
                _isLoading.value = false
                if (response.isSuccessful) {
                    val responseBody = response.body()
                    responseBody?.let { charResponse ->
                        _detail.value = charResponse
                    }
                } else {
                    Log.e(ContentValues.TAG, "onFailure: ${response.message()}")
                }
            }
            override fun onFailure(call: Call<ResultsItem>, t: Throwable) {
                _isLoading.value = false
                Log.e(ContentValues.TAG, "onFailure: ${t.message}")
            }
        })
    }
}