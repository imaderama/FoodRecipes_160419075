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
import id.ac.ubaya.informatika.foodrecipes_160419075.model.Recipe
import id.ac.ubaya.informatika.foodrecipes_160419075.model.Recipes
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class DetailViewModel(application: Application): AndroidViewModel(application), CoroutineScope  {
    private val job = Job()
    val recipeLD = MutableLiveData<List<Recipes>>()

    private val TAG = "volleyTag"
    private var queue: RequestQueue?= null

    fun addRecipe(recipes: Recipes){
        launch {
            val db = Room.databaseBuilder(getApplication(),
                FoodRecipeDatabase::class.java, "foodrecipedb").build()
            db.recipeDao().insertAll(recipes)
        }
    }

    fun fetch(id: Int): String {
//        val student1 = Student("16055","Nonie","1998/03/28","5718444778",
//                "http://dummyimage.com/75x100.jpg/cc0000/ffffff")
        queue = Volley.newRequestQueue(getApplication())
        var url = "https://ubaya.fun/hybrid/160419075/recipedetail.php"

        val stringRequest = object :  StringRequest(
            Request.Method.POST,
            url,
            { response ->
                val sType = object : TypeToken<List<Recipes>>() { }.type
                val result = Gson().fromJson<List<Recipes>>(response, sType )
                recipeLD.value = result

//                    loadingLD.value = false
                Log.d("singlevolley", response.toString())

            },
            {
//                    loadingErrorLD.value = true
//                    loadingLD.value = false
                Log.d("singlevolley", it.toString())
            }
        ){
            override fun getParams(): MutableMap<String, String> {
                val params = HashMap<String, String>()
//                Log.d("AmbilParam", "Dapat")
                params["recipe_id"] = id.toString()
                return params
            }
        }
        stringRequest.tag = TAG
        queue?.add(stringRequest)
        return recipeLD.value.toString()
//        studentLD.value =
    }

    override val coroutineContext: CoroutineContext
        get() = job + Dispatchers.Main
}