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

class DiscoverViewModel(application: Application):AndroidViewModel(application), CoroutineScope {
    val recipesLD = MutableLiveData<List<Recipes>>()
    val loadingErrorLD = MutableLiveData<Boolean>()
    val loadingLD = MutableLiveData<Boolean>()
    private var job = Job()

    private val TAG = "volleyTag"
    private var queue: RequestQueue?= null

    fun refresh(cari: String): String{
        loadingErrorLD.value = false
        loadingLD.value = true

        queue = Volley.newRequestQueue(getApplication())
        var url = "https://ubaya.fun/hybrid/160419075/discoverrecipe.php"
        var stringRequest = object : StringRequest(
            Request.Method.POST,
            url,
            { response ->
                val sType = object : TypeToken<List<Recipes>>() { }.type
                val result = Gson().fromJson<List<Recipes>>(response, sType )
                recipesLD.value = result

//                launch {
//                    val db = Room.databaseBuilder(getApplication(),
//                        FoodRecipeDatabase::class.java, "foodrecipedb").build()
//                    recipesLD.value = db.recipeDao().selectAllRecipePublic()
//                }

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
                params["cari"] = cari
                return params
            }
        }
        stringRequest.tag = TAG
        queue?.add(stringRequest)
        return recipesLD.value.toString()
    }

    override fun onCleared() {
        super.onCleared()
        queue?.cancelAll(TAG)
    }

    override val coroutineContext: CoroutineContext
        get() = job + Dispatchers.Main
}