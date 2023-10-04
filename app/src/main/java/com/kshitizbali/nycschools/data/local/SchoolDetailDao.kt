package com.kshitizbali.nycschools.data.local

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Upsert
import com.kshitizbali.nycschools.domain.SchoolDetail

@Dao
interface SchoolDetailDao {

    @Upsert
    suspend fun upsertAll(school: SchoolDetailEntity)

    @androidx.room.Query("SELECT * FROM schooldetailentity WHERE dbn = :schoolId")
    suspend fun getSchoolDetail(schoolId: String): SchoolDetail?

}