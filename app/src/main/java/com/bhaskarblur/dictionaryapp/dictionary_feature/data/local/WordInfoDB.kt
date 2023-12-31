package com.bhaskarblur.dictionaryapp.dictionary_feature.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.bhaskarblur.dictionaryapp.dictionary_feature.data.local.dao.WordInfoDao
import com.bhaskarblur.dictionaryapp.dictionary_feature.data.local.entity.WordInfoEntity
import com.bhaskarblur.dictionaryapp.dictionary_feature.data.util.GsonParser
import com.google.gson.Gson

@Database(
    entities = [WordInfoEntity::class],
    version = 1
)
@TypeConverters(Converters::class)
abstract class WordInfoDB(
) : RoomDatabase() {

    abstract val infoDao : WordInfoDao

    companion object {
        private var instance : WordInfoDB? = null

        fun getInstance(appContext : Context) : WordInfoDB {
            synchronized(this) {
                if(instance !=null) {
                    return instance!!
                }
                else {
                    instance = Room.databaseBuilder(context = appContext, WordInfoDB::class.java,
                        "wordinfodb").addTypeConverter(Converters(GsonParser(Gson()))).build()
                }
            }
            return instance!!
        }
    }
}