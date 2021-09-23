package com.example.mimiuchi_2.presenter.fragment

import com.example.mimiuchi_2.model.api.ApiService
import com.example.mimiuchi_2.model.api.Response.CouponData
import com.example.mimiuchi_2.presenter.BasePresenter
import com.example.mimiuchi_2.presenter.BaseView

class CouponContract {
    interface Presenter: BasePresenter {
        fun getApiService(): ApiService
        fun apirequest(apiService: ApiService)

    }
    interface View: BaseView<Presenter> {
        fun showError()
        fun listCreate(list:List<CouponData>)
    }
}