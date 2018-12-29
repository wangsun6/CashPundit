package com.wangsun.android.test1.api.routePoints

import com.wangsun.android.test1.api.params.ParamData
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiInterfaceData {

    @GET("GetDashboard")
    fun getData(
        @Query("TenantId") tenantId: Long,
        @Query("AppUserId") appUserId: Long,
        @Query("CompanyId") companyId: Long): Observable<ParamData>
}