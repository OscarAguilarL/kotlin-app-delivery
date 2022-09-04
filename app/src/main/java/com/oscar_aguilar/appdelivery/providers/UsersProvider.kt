package com.oscar_aguilar.appdelivery.providers

import com.oscar_aguilar.appdelivery.api.ApiRoutes
import com.oscar_aguilar.appdelivery.models.ResponseHttp
import com.oscar_aguilar.appdelivery.models.User
import com.oscar_aguilar.appdelivery.routes.UsersRoutes
import retrofit2.Call

class UsersProvider {
    private var usersRoutes: UsersRoutes? = null

    init {
        val api = ApiRoutes()
        usersRoutes = api.getUsersRoutes()
    }

    fun register(user: User): Call<ResponseHttp>? {
        return usersRoutes?.register(user);
    }
}
