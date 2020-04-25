package com.example.recipeapp.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


/** 'entities' refers to what class you want to keep in this database */
@Database(entities = [Recipe::class], version = 8, exportSchema = false)

abstract class RecipeDatabase : RoomDatabase() {

    /** Use this val to 1. Connect this class (RecipeDatabase) to the Dao Object and 2. Access the RecipeDatabaseDao
     * Do not need to provide a body for this variable as Room takes care of all the code
     * */
    abstract fun recipeDatabaseDao(): RecipeDatabaseDao


    /** Defining companion object allows us to add functions to RecipeDatabase class
     *  Eg. Can call 'RecipeDatabase.getInstance(context)' to instantiate new RecipeDatabase*/
    companion object {

        /**Create variable as need to turn RecipeDatabase class into a singleton
         * Singleton means cant create multiple instances of this database - must use same instance everywhere in app
         */
        @Volatile
        private var INSTANCE: RecipeDatabase? = null

        /**
         * Aims to create a single, only instance of this RecipeDatabase
         * This method can be called from the outside
         */
        fun getInstance(context: Context, scope: CoroutineScope): RecipeDatabase {

            /**
             * synchronized' means only one thread at a time can access this method
             * This prevents two threads from accessing method at same time which could create multiple instances of database
             */
            synchronized(this) {

                /** Copy the current value of INSTANCE to a local variable so Kotlin can smart cast.
                 *  Smart cast is only available to local variables.
                 */
                var instance = INSTANCE

                /** Only want to instantiate this database if we DONT have an instance already
                 * If we DO already have instance, just skip past the code and return the pre-existing database
                 * */
                if (instance == null) {
                    /**
                     * Cant just create new instance of RecipeDatabase like you usually would for normal object as this is abstract class
                     * Instead, use a databasebuilder provided by Room.
                     */
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        RecipeDatabase::class.java,
                        "recipe_database"
                    )
                        /**
                         * When we create new version of database (ie. increment version number) , need to tell Room how to migrate to new schema
                         * This method simply deletes old database and make it from scratch
                         * So if you use new version number, you start with new database
                         */
                        .fallbackToDestructiveMigration()
                        .build()
                    /** Assign INSTANCE to the newly created database */
                    INSTANCE = instance
                }
                /** Return instance; smart cast to be non-null */
                return instance
            }

        }

    }


}