package com.noatnoat.base.data.readingdatabase

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query


@Dao
interface ReadingDao {
    @Query("SELECT * FROM chapters")
    fun getAllChapters(): List<Chapter>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertChapter(chapter: Chapter)

    @Delete
    fun deleteChapter(chapter: Chapter)
}
