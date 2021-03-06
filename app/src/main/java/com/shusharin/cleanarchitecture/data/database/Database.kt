package com.shusharin.cleanarchitecture.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import javax.inject.Inject


@Database(entities = [ProductDbModel::class,], version = 1, exportSchema = false)
abstract class Database :RoomDatabase() {
    abstract fun dao(): DAO

}