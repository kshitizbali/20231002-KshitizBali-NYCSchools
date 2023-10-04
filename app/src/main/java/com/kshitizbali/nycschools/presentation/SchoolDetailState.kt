package com.kshitizbali.nycschools.presentation

import com.kshitizbali.nycschools.domain.SchoolDetail

/**
 * State class for status/data updates for School Detail screen.
 */
data class SchoolDetailState(
    val schoolDetail: SchoolDetail? = null,
    val isLoading: Boolean = false,
    val error: String? = null
)
