package com.kshitizbali.nycschools.data.local

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Upsert

@Dao
interface SchoolDao {

    @Upsert
    suspend fun upsertAll(schools: List<SchoolEntity>)

    @androidx.room.Query("SELECT * FROM schoolentity")
    fun pagingSource(): PagingSource<Int,SchoolEntity>

    @androidx.room.Query("DELETE FROM schoolentity")
    suspend fun clearAll()
}