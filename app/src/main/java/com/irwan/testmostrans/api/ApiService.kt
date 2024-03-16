package com.irwan.testmostrans.api

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {
    @GET("character")
    fun getCharacter(): Call<CharResponse>

    @GET("character/{id}")
    fun getDetail(
        @Path("id") id: Int
    ): Call<ResultsItem>
}