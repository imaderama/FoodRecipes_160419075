package id.ac.ubaya.informatika.foodrecipes_160419075.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import id.ac.ubaya.informatika.foodrecipes_160419075.model.Ingredients
import id.ac.ubaya.informatika.foodrecipes_160419075.model.Recipes
import id.ac.ubaya.informatika.foodrecipes_160419075.util.buildDB
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class CreatePrepAndIngViewModel(application: Application): AndroidViewModel(application), CoroutineScope {
    private val job = Job()
    val ingredentsLD = MutableLiveData<Ingredients>()

    private val TAG = "volleyTag"
    private var queue: RequestQueue?= null

    fun addIngredients(ingredients: List<Ingredients>) {
        launch {
//            val db = Room.databaseBuilder(getApplication(),
//                TodoDatabase::class.java, "tododb").build()
            val db = buildDB(getApplication())
            db.recipeDao().insertAllIngredients(ingredients)
        }
    }

    fun addIngredient(ingredients: Ingredients) {
        queue = Volley.newRequestQueue(getApplication())
        var url = "https://ubaya.fun/hybrid/160419075/addingredient.php"

        val stringRequest = object : StringRequest(
            Request.Method.POST, url,
            { response ->
                val sType = object : TypeToken<Ingredients>() { }.type
                val result = Gson().fromJson<Ingredients>(response, sType )
                ingredentsLD.value = result

//                launch {
////            val db = Room.databaseBuilder(getApplication(),
////                TodoDatabase::class.java, "tododb").build()
//                    val db = buildDB(getApplication())
//                    db.recipeDao().insertAll(recipe)
//                }
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
                params["recipe_id"] = ingredients.recipe_id_ing.toString()
                params["item"] = ingredients.item.toString()
                params["amount"] = ingredients.amount.toString()
                return params
            }
        }

        stringRequest.tag = TAG
        queue?.add(stringRequest)
//        launch {
////            val db = Room.databaseBuilder(getApplication(),
////                TodoDatabase::class.java, "tododb").build()
//            val db = buildDB(getApplication())
//            db.recipeDao().insertAllIngredient(ingredients)
//        }
    }

    fun deleteAllIngredients() {
        launch {
//            val db = Room.databaseBuilder(getApplication(),
//                TodoDatabase::class.java, "tododb").build()
            val db = buildDB(getApplication())
            db.recipeDao().deleteAllIngredients()
        }
    }

    override val coroutineContext: CoroutineContext
        get() = job + Dispatchers.Main
}