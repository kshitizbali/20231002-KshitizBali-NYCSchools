package com.kshitizbali.nycschools.data.remote

import com.squareup.moshi.Json

data class SchoolDto(
    @field:Json(name = "dbn")
    val id: String?,
    @field:Json(name = "school_name")
    val name: String?,
    @field:Json(name = "phone_number")
    val phone: String?,
    @field:Json(name = "school_email")
    val email: String?,
    @field:Json(name = "fax_number")
    val fax: String?,
    @field:Json(name = "website")
    val website: String?,
    @field:Json(name = "total_students")
    val totalStudents: String?,
    @field:Json(name = "overview_paragraph")
    val overview: String?,
    @field:Json(name = "bus")
    val bus: String?,
    @field:Json(name = "subway")
    val subway: String?,
    @field:Json(name = "city")
    val city: String?,
    @field:Json(name = "location")
    val location: String?,
    @field:Json(name = "longitude")
    val lon: String?,
    @field:Json(name = "latitude")
    val lat: String?,
    @field:Json(name = "extracurricular_activities")
    val activities: String?,
    @field:Json(name = "school_sports")
    val sports: String?,
    @field:Json(name = "finalgrades")
    val grades: String?
)