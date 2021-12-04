package id.ac.ubaya.informatika.foodrecipes_160419075.model

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity
data class Recipes(
    var name:String?,
    var category: String?,
    var likes:Int?,
    var poster:String?
){
    @PrimaryKey(autoGenerate = true)
    var recipe_id:Int = 0
}

class Ingredients(
    val recipe_id_ing:Int?,
    val item:String?,
    val amount:String?
){
    @PrimaryKey(autoGenerate = true)
    val ingredient_id:Int = 0
}

class Preparations(
    val recipe_id_prep:Int?,
    val step:Int?,
    val description:String?
){
    @PrimaryKey(autoGenerate = true)
    val preparation_id:Int = 0
}

data class Recipe(
    val recipe_id:Int?,
    val name:String?,
    val category: String?,
    val likes:Int?,
    val poster:String?
)

class Ingredient(
    val ingredient_id:Int?,
    val recipe_id_ing:Int?,
    val item:String?,
    val amount:String?
)

class Preparation(
    val preparation_id:Int?,
    val recipe_id_prep:Int?,
    val step:Int?,
    val description:String?
)

class Grocery(
    val nama:String?,
    val harga:Int?,
    val jumlah:Int?,
    val gambar:String?
)