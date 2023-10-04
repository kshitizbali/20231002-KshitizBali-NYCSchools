package com.kshitizbali.nycschools.domain.repository

import com.kshitizbali.nycschools.data.remote.SchoolDetailDto
import com.kshitizbali.nycschools.domain.util.Resource

/**
 * Repository for School Details.
 */
interface SchoolRepository {
    suspend fun getSchoolDetail(dbn: String): Resource<List<SchoolDetailDto>>
}