package com.example.seng449_bonusmvvm.view

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.seng449_bonusmvvm.Country
import com.example.seng449_bonusmvvm.R.layout.country_item
import com.example.seng449_bonusmvvm.util.getProgressDrawable
import com.example.seng449_bonusmvvm.util.loadImage

class CountryListAdapter(var countries: ArrayList<Country>): RecyclerView.Adapter<CountryListAdapter.CountryViewHolder>() {

    fun updateCountries(newCountries:List<Country>){
        countries.clear()
        countries.addAll(newCountries)
        notifyDataSetChanged()
    }

    class CountryViewHolder(view: View): RecyclerView.ViewHolder(view){

        private val imageView = view.imageView
        private val countryCapital = view.capitalItemCountry
        private val countryName = view.nameItemCountry
        private val progressDrawable = getProgressDrawable(view.context)

        fun bind(country: Country){
            countryName.text = country.countryName
            countryCapital.text = country.capital
            imageView.loadImage(country.flag, progressDrawable)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, p1: Int)  = CountryViewHolder(
        LayoutInflater.from(parent.context).inflate(country_item, parent, false)
    )

    override fun onBindViewHolder(holder: CountryViewHolder, position: Int) {
        holder.bind(countries[position])
    }

    override fun getItemCount(): Int {
        return countries.size
    }
}