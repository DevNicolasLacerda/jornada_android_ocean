package com.oceanbrasil.ocean_jornada_android_maio_2023.model.source.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.oceanbrasil.ocean_jornada_android_maio_2023.model.source.local.hint.HintDao
import com.oceanbrasil.ocean_jornada_android_maio_2023.model.source.local.hint.HintEntity

@Database(entities = [HintEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun hintDao(): HintDao

    companion object {
        private val onCreateCallback = object : Callback() {
            override fun onCreate(db: SupportSQLiteDatabase) {
                instance?.let {
                    Thread {
                        val hintDao = it.hintDao()

                        val hintEntity = HintEntity(1, "Brigadeiro", 10.0, 70.0)
                        val hintEntity2 = HintEntity(2, "Trianon Masp", 20.0, 60.0)
                        val hintEntity3 = HintEntity(3, "Paraíso", 30.0, 50.0)
                        val hintEntity4 = HintEntity(4, "Consolação", 40.0, 40.0)

                        hintDao.insert(hintEntity)
                        hintDao.insert(hintEntity2)
                        hintDao.insert(hintEntity3)
                        hintDao.insert(hintEntity4)
                    }.start()
                }
            }
        }

        private var instance: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase {
            return instance ?: Room.databaseBuilder(
                context.applicationContext,
                AppDatabase::class.java,
                "app_database"
            )
//                .addCallback(onCreateCallback)
                .build().also { instance = it }
        }
    }
}
