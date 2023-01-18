package com.example.seng449_bonusmvvm.view


import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.ProgressBar
import android.widget.TextView
import com.example.seng449_bonusmvvm.Country
import com.example.seng449_bonusmvvm.R
import com.example.seng449_bonusmvvm.viewmodel.ListViewModel

class MainActivity : AppCompatActivity() {

    lateinit var viewModel: ListViewModel
    private val countriesAdapter = CountryListAdapter(arrayListOf())
    private val list_error: TextView = findViewById(R.id.list_error)
    private val countriesList: RecyclerView = findViewById(R.id.countriesList)
    private val loading_view: ProgressBar = findViewById(R.id.loading_view)
    private val swipeRefreshLayout: SwipeRefreshLayout  = findViewById(R.id.swipeRefreshLayout)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportActionBar?.hide()

        viewModel = ViewModelProviders.of(this).get(ListViewModel::class.java)
        viewModel.refresh()

        countriesList.apply{
            layoutManager = LinearLayoutManager(context)
            adapter = countriesAdapter
        }

        swipeRefreshLayout.setOnRefreshListener {
            swipeRefreshLayout.isRefreshing = false
            viewModel.refresh()
        }

        observeViewModel()
    }

    fun observeViewModel(){
        viewModel.countries.observe(this,  androidx.lifecycle.Observer { countries:List<Country> ->
            countries.let {
                countriesList.visibility = View.VISIBLE
                countriesAdapter.updateCountries(it)
            }
        })

        viewModel.countryLoadError.observe(this, androidx.lifecycle.Observer { isError: Boolean? ->
            isError.let { list_error.visibility = if (it == true) View.VISIBLE else View.GONE}
        })

        viewModel.loading.observe(this, androidx.lifecycle.Observer { isLoading: Boolean? ->
            isLoading.let {
                loading_view.visibility = if(it == true) View.VISIBLE else View.GONE
                if (it == true){
                    list_error.visibility = View.GONE
                    countriesList.visibility = View.GONE
                }
            }
        })
    }
}