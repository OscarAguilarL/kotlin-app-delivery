package com.oscar_aguilar.appdelivery.api

import com.oscar_aguilar.appdelivery.routes.UsersRoutes

class ApiRoutes {
    private val API_URL = "http://172.28.241.87:3000/"
    private val retrofit = RetroFitClient()

    fun getUsersRoutes(): UsersRoutes {
        return retrofit.getClient(API_URL).create(UsersRoutes::class.java)
    }
}