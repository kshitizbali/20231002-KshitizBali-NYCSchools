package com.kshitizbali.nycschools.data.remote

import com.kshitizbali.nycschools.domain.util.Resource
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Api class defining our api call Get requests.
 */
interface SchoolApi {

    @GET("s3k6-pzi2.json")
    suspend fun getSchools(
        @Query("\$limit") limit: Int,
        @Query("\$offset") offset: Int
    ): List<SchoolDto>

    /**
     * FYI : THERE ARE SOME SCHOOLS WHICH DONT HAVE DETAIL DATA SO
     * APP WILL RETURN A ERROR MESSAGE IN THE DETAIL SCREEN.
     */
    @GET("f9bf-2cp4.json")
    suspend fun getSchoolDetail(
        @Query("dbn") dbn: String
    ): List<SchoolDetailDto>

    companion object{
        const val BASE_URL = "https://data.cityofnewyork.us/resource/";
    }
}