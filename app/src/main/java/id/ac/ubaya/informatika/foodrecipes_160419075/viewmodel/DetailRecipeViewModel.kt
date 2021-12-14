package id.ac.ubaya.informatika.foodrecipes_160419075.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import id.ac.ubaya.informatika.foodrecipes_160419075.model.Ingredients
import id.ac.ubaya.informatika.foodrecipes_160419075.model.MyRecipes
import id.ac.ubaya.informatika.foodrecipes_160419075.model.Preparations
import id.ac.ubaya.informatika.foodrecipes_160419075.util.buildDB
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class DetailRecipeViewModelViewModel(application: Application):AndroidViewModel(application), CoroutineScope {
    private val job = Job()
    val recipeLD = MutableLiveData<MyRecipes>()
    val ingredientLD = MutableLiveData<Ingredients>()
    val preparationLD = MutableLiveData<Preparations>()

    fun fetch(uuid:Int){
        launch {
            val db = buildDB(getApplication())
            recipeLD.value = db.recipeDao().selectMyRecipes(uuid)
        }
    }

    fun update(name:String, category:String, poster:String, uuid:Int){
        launch {
            val db = buildDB(getApplication())
            db.recipeDao().updateMyRecipe(name, category, poster, uuid)
        }
    }


    fun addRecipe(recipe:MyRecipes) {
        launch {
//            val db = Room.databaseBuilder(getApplication(),
//                TodoDatabase::class.java, "tododb").build()
            val db = buildDB(getApplication())
            db.recipeDao().insertAllMyRecipe(recipe)
        }
    }

    override val coroutineContext: CoroutineContext
        get() = job + Dispatchers.Main

}