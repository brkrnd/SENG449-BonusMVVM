package com.example.seng449_bonusmvvm.dependency_injection

import com.example.seng449_bonusmvvm.model.CountriesService
import com.example.seng449_bonusmvvm.viewmodel.ListViewModel
import dagger.Component

@Component(modules = [ApiModule::class])
interface ApiComponent {

    fun inject(service: CountriesService)

    fun inject(viewModel: ListViewModel)

}