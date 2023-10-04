package com.kshitizbali.nycschools.di

import android.content.Context
import android.util.Log
import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.room.Room
import com.kshitizbali.nycschools.BuildConfig
import com.kshitizbali.nycschools.data.local.SchoolDatabase
import com.kshitizbali.nycschools.data.local.SchoolEntity
import com.kshitizbali.nycschools.data.remote.SchoolApi
import com.kshitizbali.nycschools.data.remote.SchoolRemoteMediator
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.create
import javax.inject.Singleton

/**
 * DI module for app module.
 */
@OptIn(ExperimentalPagingApi::class)
@Module
@InstallIn(SingletonComponent::class)
object AppModule{

    @Provides
    @Singleton
    fun provideSchoolDatabase(@ApplicationContext context: Context): SchoolDatabase{
        return Room.databaseBuilder(context,SchoolDatabase::class.java,"schools.db")
            .fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    @Singleton
    fun provideSchoolApi(): SchoolApi{
        return Retrofit.Builder()
            .baseUrl(SchoolApi.BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
            .create()
    }

    @Provides
    @Singleton
    fun provideSchoolPager(schoolDb: SchoolDatabase, schoolApi: SchoolApi): Pager<Int,SchoolEntity>{
        return Pager(
            config = PagingConfig(pageSize = 20),
            remoteMediator = SchoolRemoteMediator(
                schoolDb = schoolDb,
                schoolApi = schoolApi
            ),
            pagingSourceFactory = {
                schoolDb.dao.pagingSource()
            }
        )
    }
}