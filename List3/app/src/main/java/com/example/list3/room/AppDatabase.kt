package com.example.list3.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch


@Database(entities = [TaskList::class, Exercises::class], version = 2, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun tasklistDao(): TaskListDAO
    abstract fun exerciselistDao(): ExercisesDAO

    private class DatabaseCallback(private val scope: CoroutineScope) : Callback() {
        override fun onCreate(db: SupportSQLiteDatabase) {
            super.onCreate(db)
            INSTANCE?.let { database ->
                scope.launch {
                    val listDao = database.tasklistDao()
                    val exerciseDao = database.exerciselistDao()
                    val generator = RoomDataGenerator

                    listDao.deleteAllItems()
                    exerciseDao.deleteAllExercises()

                    listDao.insertListOfItems(generator.LIST_ITEMS)
                    exerciseDao.insertListOfExercises(generator.EXERCISE_ITEMS)
                }
            }
        }
    }

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getInstance(context: Context, scope: CoroutineScope) : AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "list3_db"
                )
                 .addCallback(DatabaseCallback(scope))
                 .build()
                INSTANCE = instance
                instance
            }
        }
    }
}