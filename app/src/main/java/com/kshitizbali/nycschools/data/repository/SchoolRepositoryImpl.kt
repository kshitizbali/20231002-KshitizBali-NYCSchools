package com.kshitizbali.nycschools.data.repository

import com.kshitizbali.nycschools.data.remote.SchoolApi
import com.kshitizbali.nycschools.data.remote.SchoolDetailDto
import com.kshitizbali.nycschools.domain.repository.SchoolRepository
import com.kshitizbali.nycschools.domain.util.Resource
import java.lang.Exception
import javax.inject.Inject

/**
 * Repository implementation for School Detail.
 */
class SchoolRepositoryImpl @Inject constructor(
    private val schoolApi: SchoolApi
): SchoolRepository {
    override suspend fun getSchoolDetail(dbn: String): Resource<List<SchoolDetailDto>> {
        return try{
            Resource.Success(
                data =  schoolApi.getSchoolDetail(dbn = dbn)
            )
        }catch (e: Exception){
            e.printStackTrace()
            Resource.Error(e.message ?: "An unknown error occurred.")
        }
    }

}