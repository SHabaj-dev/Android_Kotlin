package com.shabaj.covid_19.countryList.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.shabaj.covid_19.countryList.repository.CountryListRepository
import com.shabaj.covid_19.countryList.model.CountryDataResponse

class CountryListViewModel: ViewModel() {
    private var countryListRepository: CountryListRepository =
        CountryListRepository()
    lateinit var responseLiveData: LiveData<CountryDataResponse>

    fun fetchCountryDataList() {
        responseLiveData = countryListRepository.fetchCountryListData()
    }
}
