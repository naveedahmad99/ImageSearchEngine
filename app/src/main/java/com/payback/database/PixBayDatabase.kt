package com.payback.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.payback.models.Photo
import com.payback.models.PhotoSearchResult


@Database(
    entities = [
        Photo::class,
        PhotoSearchResult::class],
    version = 9,
    exportSchema = false
)
@TypeConverters(Converters::class)
abstract class PixBayDatabase : RoomDatabase() {
    abstract fun photoDao(): PhotoDoa
}