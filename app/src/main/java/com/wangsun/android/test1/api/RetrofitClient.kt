package com.wangsun.android.test1.api

import retrofit2.Retrofit
import com.google.gson.GsonBuilder
import com.wangsun.android.test1.ApplicationMy
import com.wangsun.android.test1.api.routePoints.ApiInterfaceData
import okhttp3.Cache
import okhttp3.CacheControl
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.io.File
import java.util.concurrent.TimeUnit
import javax.xml.datatype.DatatypeConstants.SECONDS




object RetrofitClient {

    var BASE_URL = "http://service.bank4cast.com/api/MobileAPI/"
    var retrofit: Retrofit
//    val cacheSize: Long= (5 * 1024 * 1024).toLong()
//    val myCache = Cache(ApplicationMy.instance.cacheDir, cacheSize)

    init {

        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY

        val okHttpClient = OkHttpClient.Builder()
            .connectTimeout(2, TimeUnit.MINUTES)
            .readTimeout(2, TimeUnit.MINUTES)
            .writeTimeout(2, TimeUnit.MINUTES)
            .addInterceptor(interceptor)
            .build()
//            .addInterceptor { chain ->
//                var request = chain.request()
//                request = if (ApplicationMy.hasNetwork())
//                    request.newBuilder().header("Cache-Control", "public, max-age=" + 5).build()
//                else
//                    request.newBuilder().header("Cache-Control", "public, only-if-cached, max-stale=" + 60 * 60 * 24 * 7).build()
//                chain.proceed(request)
//            }
//            .build()

        val gson = GsonBuilder().setLenient().create()

        retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
    }




    //Api Interface
    fun ApiData(): ApiInterfaceData = retrofit.create(ApiInterfaceData::class.java)
}