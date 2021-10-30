package id.ac.ubaya.informatika.foodrecipes_160419075.model

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = arrayOf(Recipes::class), version = 1)
abstract class FoodRecipeDatabase: RoomDatabase() {
    abstract fun recipeDao():FoodRecipeDao

    companion object {
        @Volatile private var instance:FoodRecipeDatabase ?= null
        private val LOCK = Any()

        private fun buildDatabase(context: Context) = Room.databaseBuilder(
            context.applicationContext,
            FoodRecipeDatabase::class.java,
            "foodrecipedb"
        ).build()

        operator fun invoke(context: Context) = instance ?: synchronized(LOCK){
            instance ?: buildDatabase(context).also {
                instance = it
            }
        }
    }
}
