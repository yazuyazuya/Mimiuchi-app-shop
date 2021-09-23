package com.example.mimiuchi_2.presenter.activity

import com.example.mimiuchi_2.model.api.ApiService
import com.example.mimiuchi_2.presenter.BasePresenter
import com.example.mimiuchi_2.presenter.BaseView

class CouponEditContract {
    interface Presenter: BasePresenter {
        fun getApiService(): ApiService
        fun editRequest(apiService: ApiService)
        fun deleteRequest(apiService: ApiService)
        fun editStart()
        fun deleteStart()

    }
    interface View:
        BaseView<Presenter> {
        fun showError()
    }
}