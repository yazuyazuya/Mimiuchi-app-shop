package com.example.mimiuchi_2.presenter.activity

import android.util.Log
import com.example.mimiuchi_2.R
import com.example.mimiuchi_2.model.api.ApiService
import com.example.mimiuchi_2.model.MainRepositoryImpl
import com.example.mimiuchi_2.presenter.Globals
import kotlinx.android.synthetic.main.activity_menu_register.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import okhttp3.RequestBody
import okhttp3.MediaType
import java.io.File


class MenuRegisterPresenter(private val view: MenuRegisterContract.View):
    MenuRegisterContract.Presenter {
    override fun getApiService(): ApiService = MainRepositoryImpl().initApiService()

    override fun apirequest(apiService: ApiService) {

        val menuData = view.createMenuData()

        val globals = Globals.instance as Globals



        var requestBody = RequestBody.create(MediaType.parse("multipart/form-data"), globals.file)

        val call = apiService.menuAdd(menuData.menuName,menuData.limit,menuData.value,requestBody,menuData.releaseCount,menuData.shopID)

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
                    view.success()
                }
            }
        })
    }

    override fun start() {
        apirequest(getApiService())
    }

}