package com.oscar_aguilar.appdelivery.routes

import com.oscar_aguilar.appdelivery.models.ResponseHttp
import com.oscar_aguilar.appdelivery.models.User
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface UsersRoutes {
    @POST("api/users/create")
    fun register(@Body user: User): Call<ResponseHttp>
}
