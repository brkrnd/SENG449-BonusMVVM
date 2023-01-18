package com.example.seng449_bonusmvvm.model

import com.example.seng449_bonusmvvm.Country
import io.reactivex.Single
import retrofit2.http.GET

interface CountriesApi {

    @GET("DevTides/countries/master/countriesV2.json")
    fun getCountries(): Single<List<Country>>

    @GET("endpoint2")
    fun getEndpoint2Data()

}