package com.noatnoat.discover.data.datasource.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverter
import androidx.room.TypeConverters
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.noatnoat.discover.data.datasource.database.model.chapter_model.ChapterDataEntity
import com.noatnoat.discover.data.datasource.database.model.chapter_model.ChapterEntity
import com.noatnoat.discover.data.datasource.database.model.chapter_model.ChapterResponseEntity
import com.noatnoat.discover.data.datasource.database.model.chapter_model.ChapterResponseImageEntity
import com.noatnoat.discover.data.datasource.database.model.search_model.SearchDataEntity
import com.noatnoat.discover.data.datasource.database.model.search_model.SearchResponseEntity

@Database(entities = [ChapterResponseEntity::class, ChapterResponseImageEntity::class, SearchResponseEntity::class], version = 1, exportSchema = false)
@TypeConverters(ChapterResponseEntityTypeConverter::class, ChapterResponseEntityTypeConverter.ChapterDataEntityTypeConverter::class,
    ChapterResponseEntityTypeConverter.SearchDataEntityTypeConverter::class)
internal abstract class ComicDatabase : RoomDatabase() {
    abstract fun comics(): ComicDao
}


class ChapterResponseEntityTypeConverter {
    private val gson = Gson()

    @TypeConverter
    fun fromStringList(list: List<String?>?): String? {
        if (list == null) {
            return null
        }
        val type = object : TypeToken<List<String?>>() {}.type
        return gson.toJson(list, type)
    }

    @TypeConverter
    fun toStringList(string: String?): List<String?>? {
        if (string == null) {
            return null
        }
        val type = object : TypeToken<List<String?>>() {}.type
        return gson.fromJson(string, type)
    }

    @TypeConverter
    fun fromChapterEntity(chapter: ChapterEntity?): String? {
        if (chapter == null) {
            return null
        }
        val type = object : TypeToken<ChapterEntity>() {}.type
        return gson.toJson(chapter, type)
    }

    @TypeConverter
    fun toChapterEntity(string: String?): ChapterEntity? {
        if (string == null) {
            return null
        }
        val type = object : TypeToken<ChapterEntity>() {}.type
        return gson.fromJson(string, type)
    }


    class ChapterDataEntityTypeConverter {
        private val gson = Gson()

        @TypeConverter
        fun fromChapterDataEntityList(data: List<ChapterDataEntity>?): String? {
            if (data == null) {
                return null
            }
            val type = object : TypeToken<List<ChapterDataEntity>>() {}.type
            return gson.toJson(data, type)
        }

        @TypeConverter
        fun toChapterDataEntityList(dataString: String?): List<ChapterDataEntity>? {
            if (dataString == null) {
                return null
            }
            val type = object : TypeToken<List<ChapterDataEntity>>() {}.type
            return gson.fromJson(dataString, type)
        }
    }

    class SearchDataEntityTypeConverter {
        private val gson = Gson()

        @TypeConverter
        fun fromSearchDataEntityList(data: List<SearchDataEntity>?): String? {
            if (data == null) {
                return null
            }
            val type = object : TypeToken<List<SearchDataEntity>>() {}.type
            return gson.toJson(data, type)
        }

        @TypeConverter
        fun toSearchDataEntityList(dataString: String?): List<SearchDataEntity>? {
            if (dataString == null) {
                return null
            }
            val type = object : TypeToken<List<SearchDataEntity>>() {}.type
            return gson.fromJson(dataString, type)
        }
    }



}


