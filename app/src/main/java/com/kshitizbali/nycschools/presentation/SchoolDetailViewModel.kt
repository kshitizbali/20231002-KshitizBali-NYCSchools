package com.kshitizbali.nycschools.presentation

import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.runtime.*
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.room.withTransaction
import com.kshitizbali.nycschools.data.local.SchoolDatabase
import com.kshitizbali.nycschools.data.mappers.toSchoolDetail
import com.kshitizbali.nycschools.data.mappers.toSchoolDetailEntity
import com.kshitizbali.nycschools.data.remote.SchoolApi
import com.kshitizbali.nycschools.domain.SchoolDetail
import com.kshitizbali.nycschools.domain.repository.SchoolRepository
import com.kshitizbali.nycschools.domain.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.lang.Exception
import javax.inject.Inject

@HiltViewModel
class SchoolDetailViewModel @Inject constructor(
    private val schoolDb : SchoolDatabase,
    private val schoolRepository: SchoolRepository
): ViewModel(){

    var schoolDetailState by mutableStateOf(SchoolDetailState())
        internal  set

    fun loadSchoolDetails(schoolId: String){
        viewModelScope.launch {
            when(val result = schoolRepository.getSchoolDetail(schoolId)){
                is Resource.Success -> {
                    if(!result.data.isNullOrEmpty()){
                        schoolDb.withTransaction {
                            result.data?.let { schoolDb.schoolDetailDao.upsertAll(it.first().toSchoolDetailEntity()) }
                        }
                        val schoolDetailEntity = result.data?.first()?.toSchoolDetailEntity();
                        if (schoolDetailEntity != null) {
                            schoolDetailState  = schoolDetailState.copy(
                                schoolDetail = schoolDetailEntity.toSchoolDetail(),
                                isLoading = false,
                                error = null
                            )
                        } else{
                            schoolDetailState = schoolDetailState.copy(
                                schoolDetail = null,
                                isLoading = false,
                                error = "No data found for the school."
                            )
                        }
                    }else {

                        schoolDetailState = schoolDetailState.copy(
                            schoolDetail = null,
                            isLoading = false,
                            error = "No data found for the school."
                        )
                    }

                }
                is Resource.Error -> {
                    var schoolData: SchoolDetail? = null
                    schoolDb.withTransaction {
                      schoolData =  schoolDb.schoolDetailDao.getSchoolDetail(schoolId)
                    }
                    if(schoolData != null){
                        schoolDetailState = schoolDetailState.copy(
                            schoolDetail = schoolData,
                            isLoading = false,
                            error = "No internet connection."
                        )
                    } else {
                        schoolDetailState = schoolDetailState.copy(
                            schoolDetail = null,
                            isLoading = false,
                            error = "No internet connection/data."
                        )
                    }

                }
            }
        }
    }
}