package com.example.mimiuchi_2.presenter.fragment

import com.example.mimiuchi_2.model.api.ApiService
import com.example.mimiuchi_2.model.api.Response.ShopData
import com.example.mimiuchi_2.presenter.BasePresenter
import com.example.mimiuchi_2.presenter.BaseView

class ShopContract {
    interface Presenter: BasePresenter {
        fun getApiService(): ApiService
        fun apirequest(apiService: ApiService)

    }
    interface View:
        BaseView<Presenter> {
        fun showError()
        fun showData(data:ShopData)
    }
}