package com.example.mimiuchi_2.presenter.activity

import android.util.Log
import com.example.mimiuchi_2.model.MainRepositoryImpl
import com.example.mimiuchi_2.model.api.ApiService
import com.example.mimiuchi_2.model.api.MainRepository
import com.example.mimiuchi_2.presenter.Globals
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MenuEditPresenter(private val view:MenuEditContract.View):MenuEditContract.Presenter {
    override fun editStart() {
        editRequest(getApiService())
    }

    override fun deleteStart() {
        deleteRequest(getApiService())
    }

    override fun getApiService(): ApiService = MainRepositoryImpl().initApiService()

    override fun editRequest(apiService: ApiService) {

    }

    override fun deleteRequest(apiService: ApiService) {
        val globals = Globals.instance as Globals
        val call = apiService.menuDelete(globals.shopID,globals.menuID)
        call.enqueue(object : Callback<String> {
            override fun onFailure(call: Call<String>, t: Throwable) {
                Log.d("fetchItems", "response fail")
                Log.d("fetchItems", "throwable :$t")
            }

            override fun onResponse(call: Call<String>, response: Response<String>) {
                if (!response.isSuccessful) {
                    Log.d("fetchItems", "not response")
                } else {
                    val responseItem = response.body()

                }
            }

        })
    }

    override fun start() {

    }
}