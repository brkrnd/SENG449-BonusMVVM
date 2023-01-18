package com.example.seng449_bonusmvvm.model

import com.example.seng449_bonusmvvm.Country
//import com.example.seng449_bonusmvvm.dependency_injection.DaggerApiComponent
import io.reactivex.Single
import javax.inject.Inject

class CountriesService {

    @Inject
    lateinit var api: CountriesApi

    init {
        DaggerApiComponent.create.inject(this)
    }
    fun getCountries(): Single<List<Country>>{
        return api.getCountries()
    }
}