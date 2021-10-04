package id.ac.ubaya.informatika.foodrecipes_160419075.model

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