package id.ac.ubaya.informatika.foodrecipes_160419075.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.room.Room
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import id.ac.ubaya.informatika.foodrecipes_160419075.model.FoodRecipeDatabase
import id.ac.ubaya.informatika.foodrecipes_160419075.model.Recipe
import id.ac.ubaya.informatika.foodrecipes_160419075.model.Recipes
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class ListViewModel(application: Application):AndroidViewModel(application), CoroutineScope {
    val recipessLD = MutableLiveData<List<Recipes>>()

//    val recipesLD = MutableLiveData<List<Recipe>>()
    val loadingErrorLD = MutableLiveData<Boolean>()
    val loadingLD = MutableLiveData<Boolean>()
    private var job = Job()

    private val TAG = "volleyTag"
    private var queue: RequestQueue?= null

    fun refresh() {
        loadingErrorLD.value = false
        loadingLD.value = true



        queue = Volley.newRequestQueue(getApplication())
        var url = "https://ubaya.fun/hybrid/160419075/recipelist3.php"

        val stringRequest = StringRequest(
            Request.Method.GET, url,
            { response ->
                val sType = object : TypeToken<List<Recipes>>() { }.type
                val result = Gson().fromJson<List<Recipes>>(response, sType )
                recipessLD.value = result

                launch {
                    val db = Room.databaseBuilder(getApplication(),
                        FoodRecipeDatabase::class.java, "foodrecipedb").build()
                    db.recipeDao().deleteRecipe(result)
                    db.recipeDao().insertAllRecipes(result)
                }
                loadingLD.value = false
                Log.d("showvolley", response.toString())

            },
            {
                loadingErrorLD.value = true
                loadingLD.value = false
                Log.d("showvolley", it.toString())
            })

        stringRequest.tag = TAG
        queue?.add(stringRequest)

        launch {
            val db = Room.databaseBuilder(getApplication(),
                FoodRecipeDatabase::class.java, "foodrecipedb").build()
//            db.recipeDao().insertAll()
            recipessLD.value = db.recipeDao().selectAllRecipePublic()

            loadingLD.value = false
        }
    }

    fun clearRecipe(recipes: Recipes) {
        launch {
            val db = Room.databaseBuilder(getApplication(),
                FoodRecipeDatabase::class.java, "foodrecipedb").build()
            recipessLD.value = db.recipeDao().selectAllRecipePublic()
        }
    }

    override fun onCleared() {
        super.onCleared()
        queue?.cancelAll(TAG)
    }

    override val coroutineContext: CoroutineContext
        get() = job + Dispatchers.Main
}