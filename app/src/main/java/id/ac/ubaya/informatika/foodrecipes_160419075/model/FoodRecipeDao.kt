package id.ac.ubaya.informatika.foodrecipes_160419075.model

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface FoodRecipeDao {
    @Insert
    suspend fun insertAll(recipes: Recipes)

    @Insert
    suspend fun insertAllRecipes(recipes:List<Recipes>)

    @Query("SELECT * FROM recipes")
    suspend fun selectAllRecipe():List<Recipes>

    @Query("SELECT * FROM recipes WHERE recipe_id = :id")
    suspend fun selectRecipe(id:Int):Recipes

    @Delete
    suspend fun deleteRecipe(recipes: Recipes)

    @Delete
    suspend fun deleteRecipe(recipes:List<Recipes>)
}
