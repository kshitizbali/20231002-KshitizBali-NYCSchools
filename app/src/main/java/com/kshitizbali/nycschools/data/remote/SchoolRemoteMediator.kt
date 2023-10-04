package com.kshitizbali.nycschools.data.remote

import android.util.Log
import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import com.kshitizbali.nycschools.data.local.SchoolDatabase
import com.kshitizbali.nycschools.data.local.SchoolEntity
import com.kshitizbali.nycschools.data.mappers.toSchoolEntity
import kotlinx.coroutines.delay
import retrofit2.HttpException
import java.io.IOException

/**
 * Mediator class for paginating schools list data and
 * also backs to room db data (if it exists) in case of no
 * internet.
 */
@OptIn(ExperimentalPagingApi::class)
class SchoolRemoteMediator(
    private val schoolDb : SchoolDatabase,
    private val schoolApi: SchoolApi
): RemoteMediator<Int,SchoolEntity>() {
    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, SchoolEntity>
    ): MediatorResult {
        return try {
            val loadKey = when(loadType){
                LoadType.REFRESH -> 0
                LoadType.PREPEND -> return MediatorResult.Success(
                    endOfPaginationReached = true
                )
                LoadType.APPEND -> {
                    val lastItem = state.lastItemOrNull()
                    if(lastItem == null){
                        0
                    }else {
                        (lastItem._id?.div(state.config.pageSize))?.plus(1)
                    }
                }
            }

            val schools = loadKey?.let {
                schoolApi.getSchools(limit = state.config.pageSize,
                    offset = it
                )
            }

            schoolDb.withTransaction {
                if(loadType == LoadType.REFRESH){
                    schoolDb.dao.clearAll()
                }
                val schoolEntities = schools?.map { it.toSchoolEntity() }
                if (schoolEntities != null) {
                    schoolDb.dao.upsertAll(schools = schoolEntities)
                }
            }
            MediatorResult.Success(endOfPaginationReached = schools!!.isEmpty())
        }catch (e: IOException){
            MediatorResult.Error(e)
        } catch (e: HttpException) {
            MediatorResult.Error(e)
        }
    }
}