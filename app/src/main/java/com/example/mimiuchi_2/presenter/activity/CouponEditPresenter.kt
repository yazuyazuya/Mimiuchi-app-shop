package com.example.mimiuchi_2.presenter.activity

import android.util.Log
import com.example.mimiuchi_2.model.MainRepositoryImpl
import com.example.mimiuchi_2.model.api.ApiService
import com.example.mimiuchi_2.presenter.Globals
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CouponEditPresenter(private val view:CouponEditContract.View):CouponEditContract.Presenter {
    override fun getApiService(): ApiService = MainRepositoryImpl().initApiService()

    override fun editRequest(apiService: ApiService) {

    }

    override fun deleteRequest(apiService: ApiService) {
        val globals = Globals.instance as Globals
        val call = apiService.couponDelete(globals.shopID,globals.couponID)
        call.enqueue(object : Callback<String> {
            override fun onFailure(call: Call<String>, t: Throwable) {
                Log.d("fetchItems", "response fail")
                Log.d("fetchItems", "throwable :$t")            }

            override fun onResponse(call: Call<String>, response: Response<String>) {
                if (!response.isSuccessful) {
                   Log.d("fetchItems", "not response")
                } else {
                    val responseItem = response.body()

                }
            }

        })
    }

    override fun editStart() {
        editRequest(getApiService())
    }

    override fun deleteStart() {
        deleteRequest(getApiService())
    }

    override fun start() {
    }
}