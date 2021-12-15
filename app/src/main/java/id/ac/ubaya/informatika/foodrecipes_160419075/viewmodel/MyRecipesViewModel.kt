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
import id.ac.ubaya.informatika.foodrecipes_160419075.model.FoodRecipeDatabase
import id.ac.ubaya.informatika.foodrecipes_160419075.model.MyRecipes
import id.ac.ubaya.informatika.foodrecipes_160419075.model.Recipes
import id.ac.ubaya.informatika.foodrecipes_160419075.model.RecipesDraft
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class MyRecipesViewModel(application: Application):AndroidViewModel(application), CoroutineScope {
//    val recipesLD = MutableLiveData<List<Recipes>>()
    val recipesLD = MutableLiveData<List<Recipes>>()
    val loadingErrorLD = MutableLiveData<Boolean>()
    val loadingLD = MutableLiveData<Boolean>()
    val recipesDraftLD = MutableLiveData<List<Recipes>>()
    val loadingErrorDraftLD = MutableLiveData<Boolean>()
    val loadingDraftLD = MutableLiveData<Boolean>()
    private var job = Job()

    private val TAG = "volleyTag"
    private var queue: RequestQueue?= null

    /*fun refresh(category: String): String{
        loadingErrorLD.value = false
        loadingLD.value = true

        queue = Volley.newRequestQueue(getApplication())
        var url = "https://ubaya.fun/hybrid/160419075/recipelist4.php"
        var stringRequest = object : StringRequest(
                Request.Method.POST,
                url,
                { response ->
                    val sType = object : TypeToken<List<Recipes>>() { }.type
                    val result = Gson().fromJson<List<Recipes>>(response, sType )
                    recipesLD.value = result

                    loadingLD.value = false
                    Log.d("showvolley", response.toString())

                },
                {
                    loadingErrorLD.value = true
                    loadingLD.value = false
                    Log.d("showvolley", it.toString())
                }
        ){
            override fun getParams(): MutableMap<String, String> {
                val params = HashMap<String, String>()
//                Log.d("AmbilParam", "Dapat")
                params["category"] = category
                return params
            }
        }
        stringRequest.tag = TAG
        queue?.add(stringRequest)
        return recipesLD.value.toString()
    }*/

    /*fun refresh2(category: String): String{
        loadingErrorLD2.value = false
        loadingLD2.value = true

        queue = Volley.newRequestQueue(getApplication())
        var url = "https://ubaya.fun/hybrid/160419075/recipelist4.php"
        var stringRequest = object : StringRequest(
                Request.Method.POST,
                url,
                { response ->
                    val sType = object : TypeToken<List<Recipes>>() { }.type
                    val result = Gson().fromJson<List<Recipes>>(response, sType )
                    recipesDraftLD.value = result

                    loadingLD2.value = false
                    Log.d("showvolley", response.toString())

                },
                {
                    loadingErrorLD2.value = true
                    loadingLD2.value = false
                    Log.d("showvolley", it.toString())
                }
        ){
            override fun getParams(): MutableMap<String, String> {
                val params = HashMap<String, String>()
//                Log.d("AmbilParam", "Dapat")
                params["category"] = category
                return params
            }
        }
        stringRequest.tag = TAG
        queue?.add(stringRequest)
        return recipesDraftLD.value.toString()
    }*/

    fun refresh() {
        loadingErrorLD.value = false
        loadingLD.value = true

//        queue = Volley.newRequestQueue(getApplication())
//        var url = "https://ubaya.fun/hybrid/160419075/recipelist3.php"
//
//        val stringRequest = StringRequest(
//            Request.Method.GET, url,
//            { response ->
//                val sType = object : TypeToken<List<Recipes>>() { }.type
//                val result = Gson().fromJson<List<Recipes>>(response, sType )
//                recipessLD.value = result
//
//                launch {
//                    val db = Room.databaseBuilder(getApplication(),
//                        FoodRecipeDatabase::class.java, "foodrecipedb").build()
//                    db.recipeDao().deleteRecipe(result)
//                    db.recipeDao().insertAllRecipes(result)
//                }
//                loadingLD.value = false
//                Log.d("showvolley", response.toString())
//
//            },
//            {
//                loadingErrorLD.value = true
//                loadingLD.value = false
//                Log.d("showvolley", it.toString())
//            })
//
//        stringRequest.tag = TAG
//        queue?.add(stringRequest)

        launch {
            val db = Room.databaseBuilder(getApplication(),
                FoodRecipeDatabase::class.java, "foodrecipedb").build()
//            db.recipeDao().insertAll()
            recipesLD.value = db.recipeDao().selectAllRecipePublic()

            loadingLD.value = false
        }
    }

    fun refresh2() {
        loadingErrorLD.value = false
        loadingLD.value = true

//        queue = Volley.newRequestQueue(getApplication())
//        var url = "https://ubaya.fun/hybrid/160419075/recipelist3.php"
//
//        val stringRequest = StringRequest(
//            Request.Method.GET, url,
//            { response ->
//                val sType = object : TypeToken<List<Recipes>>() { }.type
//                val result = Gson().fromJson<List<Recipes>>(response, sType )
//                recipessLD.value = result
//
//                launch {
//                    val db = Room.databaseBuilder(getApplication(),
//                        FoodRecipeDatabase::class.java, "foodrecipedb").build()
//                    db.recipeDao().deleteRecipe(result)
//                    db.recipeDao().insertAllRecipes(result)
//                }
//                loadingLD.value = false
//                Log.d("showvolley", response.toString())
//
//            },
//            {
//                loadingErrorLD.value = true
//                loadingLD.value = false
//                Log.d("showvolley", it.toString())
//            })
//
//        stringRequest.tag = TAG
//        queue?.add(stringRequest)

        launch {
            val db = Room.databaseBuilder(getApplication(),
                FoodRecipeDatabase::class.java, "foodrecipedb").build()
//            db.recipeDao().insertAll()
            recipesDraftLD.value = db.recipeDao().selectAllRecipeUnPublic()

            loadingDraftLD.value = false
        }
    }


    override fun onCleared() {
        super.onCleared()
        queue?.cancelAll(TAG)
    }

    override val coroutineContext: CoroutineContext
        get() = job + Dispatchers.Main
}