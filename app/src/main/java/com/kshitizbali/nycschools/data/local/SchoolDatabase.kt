package com.kshitizbali.nycschools.data.local

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [SchoolEntity::class,SchoolDetailEntity::class],
    version = 3
)
abstract class SchoolDatabase: RoomDatabase() {

    abstract val dao: SchoolDao
    abstract val schoolDetailDao: SchoolDetailDao
}