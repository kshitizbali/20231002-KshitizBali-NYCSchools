package com.kshitizbali.nycschools.data.mappers

import com.kshitizbali.nycschools.data.local.SchoolDetailDao
import com.kshitizbali.nycschools.data.local.SchoolDetailEntity
import com.kshitizbali.nycschools.data.local.SchoolEntity
import com.kshitizbali.nycschools.data.remote.SchoolDetailDto
import com.kshitizbali.nycschools.data.remote.SchoolDto
import com.kshitizbali.nycschools.domain.School
import com.kshitizbali.nycschools.domain.SchoolDetail

/**
 * Mapper helper class to map data as needed.
 */
fun SchoolDto.toSchoolEntity(): SchoolEntity {
    return SchoolEntity(dbn = id,
    name = name,
    phone = phone,
        email = email,
        fax = fax,
        website = website,
        totalStudents = totalStudents,
        overview = overview,
        bus = bus,
        subway = subway,
        city = city,
        location = location,
        lon = lon,
        lat = lat,
        activities = activities,
        sports = sports,
        grades = grades)
}

fun SchoolDetailDto.toSchoolDetailEntity(): SchoolDetailEntity {
    return SchoolDetailEntity(dbn = dbn ?: "",
        schoolName = schoolName ?: "",
        noOfTestTakers = noOfTestTakers ?: "",
        satReadingAvgScore = satReadingAvgScore ?: "",
        satMathAvgScore = satMathAvgScore ?: "",
        satWritingAvgScore = satWritingAvgScore ?: "")
}

fun SchoolEntity.toSchool(): School {
    return School(
        id = _id.toString(),
        dbn = dbn,
        name = name,
        phone = phone,
        email = email,
        fax = fax,
        website = website,
        totalStudents = totalStudents,
        overview = overview,
        bus = bus,
        subway = subway,
        city = city,
        location = location,
        lon = lon,
        lat = lat,
        activities = activities,
        sports = sports,
        grades = grades
    )
}

fun SchoolDetailEntity.toSchoolDetail(): SchoolDetail {
    return SchoolDetail(dbn = dbn ?: "",
        schoolName = schoolName ?: "",
        noOfTestTakers = noOfTestTakers ?: "",
        satReadingAvgScore = satReadingAvgScore ?: "",
        satMathAvgScore = satMathAvgScore ?: "",
        satWritingAvgScore = satWritingAvgScore ?: "",)
}