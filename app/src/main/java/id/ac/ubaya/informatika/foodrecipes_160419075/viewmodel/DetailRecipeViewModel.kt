package id.ac.ubaya.informatika.foodrecipes_160419075.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.room.Room
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import id.ac.ubaya.informatika.foodrecipes_160419075.model.*
import id.ac.ubaya.informatika.foodrecipes_160419075.util.buildDB
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class DetailRecipeViewModelViewModel(application: Application):AndroidViewModel(application), CoroutineScope {
    private val job = Job()
//    val recipeLD = MutableLiveData<MyRecipes>()
    val recipeLD = MutableLiveData<Recipes>()
    val recipeLastLD = MutableLiveData<Recipes>()
    val ingredientLD = MutableLiveData<Ingredients>()
    val preparationLD = MutableLiveData<Preparations>()

    private val TAG = "volleyTag"
    private var queue: RequestQueue?= null

    fun fetch(uuid:Int){
        launch {
            val db = buildDB(getApplication())
            recipeLD.value = db.recipeDao().selectRecipe(uuid)
        }
    }

    fun update(name:String, category:String, poster:String, uuid:Int){
        launch {
            val db = buildDB(getApplication())
            db.recipeDao().updateMyRecipe(name, category, poster, uuid)
        }
    }


    fun addRecipe(recipe:Recipes) {
        queue = Volley.newRequestQueue(getApplication())
        var url = "https://ubaya.fun/hybrid/160419075/addrecipe.php"

        val stringRequest = object : StringRequest(
            Request.Method.POST, url,
            { response ->
                val sType = object : TypeToken<Recipes>() { }.type
                val result = Gson().fromJson<Recipes>(response, sType )
                recipeLD.value = result

                launch {
//            val db = Room.databaseBuilder(getApplication(),
//                TodoDatabase::class.java, "tododb").build()
                    val db = buildDB(getApplication())
                    db.recipeDao().insertAll(recipe)
                }
//                loadingLD.value = false
                Log.d("showvolley", response.toString())

            },
            {
//                loadingErrorLD.value = true
//                loadingLD.value = false
                Log.d("showvolley", it.toString())
            }
        ){
            override fun getParams(): MutableMap<String, String> {
                val params = HashMap<String, String>()
//                Log.d("AmbilParam", "Dapat")
                params["name"] = recipe.name.toString()
                params["category"] = recipe.category.toString()
                params["likes"] = recipe.likes.toString()
                params["poster"] = recipe.poster.toString()
                params["public"] = recipe.public_stat.toString()
                return params
            }
        }

        stringRequest.tag = TAG
        queue?.add(stringRequest)

    }

    fun deleteAllMyRecipe(id: Int) {
        launch {
//            val db = Room.databaseBuilder(getApplication(),
//                TodoDatabase::class.java, "tododb").build()
            val db = buildDB(getApplication())
            db.recipeDao().deleteRecipe(id)
        }
    }

    fun selectLastRecipe() {
        launch {
//            val db = Room.databaseBuilder(getApplication(),
//                TodoDatabase::class.java, "tododb").build()
            val db = buildDB(getApplication())
            recipeLastLD.value = db.recipeDao().selectLastRecipe()
        }
    }

    override val coroutineContext: CoroutineContext
        get() = job + Dispatchers.Main

}