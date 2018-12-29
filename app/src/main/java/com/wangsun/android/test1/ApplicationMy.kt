package com.wangsun.android.test1

import android.app.Application
import android.content.Context
import android.net.ConnectivityManager

class ApplicationMy : Application(){

    companion object {
        lateinit var instance: ApplicationMy

        fun hasNetwork(): Boolean {
            return instance.checkIfHasNetwork()
        }
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
    }


    fun checkIfHasNetwork(): Boolean {
        val cm = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val networkInfo = cm.activeNetworkInfo
        return networkInfo != null && networkInfo.isConnected
    }
}