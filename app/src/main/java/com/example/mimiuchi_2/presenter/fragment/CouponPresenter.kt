package com.example.mimiuchi_2.presenter.fragment

import android.util.Log
import com.example.mimiuchi_2.model.MainRepositoryImpl
import com.example.mimiuchi_2.model.api.ApiService
import com.example.mimiuchi_2.model.api.Response.CouponData
import com.example.mimiuchi_2.presenter.Globals
import com.google.gson.Gson
import com.google.gson.internal.`$Gson$Types`
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CouponPresenter(private val view: CouponContract.View): CouponContract.Presenter {
    override fun getApiService(): ApiService = MainRepositoryImpl().initApiService()

    override fun apirequest(apiService: ApiService) {

        val globals = Globals.instance as Globals
        val call = apiService.couponAll(globals.shopID)

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
                    val data = objectsFromJson(responseItem, CouponData::class.java)

                    if(data[0].response=="ヒットしました。"){
                        view.listCreate(data)
                    }
                }
            }

        })
    }

    override fun start() {
        apirequest(getApiService())
    }
    fun <T> objectsFromJson(json: String?, clazz: Class<T>): List<T> {
        val type = `$Gson$Types`.newParameterizedTypeWithOwner(null, ArrayList::class.java, clazz)
        return Gson().fromJson(json, type)
    }

}