package id.ac.ubaya.informatika.foodrecipes_160419075.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import id.ac.ubaya.informatika.foodrecipes_160419075.model.Ingredients
import id.ac.ubaya.informatika.foodrecipes_160419075.model.Recipe
import id.ac.ubaya.informatika.foodrecipes_160419075.util.buildDB
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import org.json.JSONObject
import kotlin.coroutines.CoroutineContext

class ListViewModelIngredient(application: Application): AndroidViewModel(application),
    CoroutineScope {
    val ingredentsLD = MutableLiveData<List<Ingredients>>()
    val loadingErrorLD = MutableLiveData<Boolean>()
    val loadingLD = MutableLiveData<Boolean>()
    private val job = Job()

    private val TAG = "volleyTag"
    private var queue: RequestQueue?= null

    fun refresh(id: String): String{
        loadingErrorLD.value = false
        loadingLD.value = true

        queue = Volley.newRequestQueue(getApplication())
        var url = "https://ubaya.fun/hybrid/160419075/listingredient.php"

        var stringRequest = object : StringRequest(
            Request.Method.POST,
            url,
            { response ->
                    val sType = object : TypeToken<List<Ingredients>>() { }.type
                    val result = Gson().fromJson<List<Ingredients>>(response, sType )
                    ingredentsLD.value = result

//                    launch {
//    //            val db = Room.databaseBuilder(getApplication(),
//    //                TodoDatabase::class.java, "tododb").build()
//                        val db = buildDB(getApplication())
//                        db.recipeDao().insertAllIngredients(result)
//                    }

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
                params["id"] = id
                return params
            }
        }
        stringRequest.tag = TAG
        queue?.add(stringRequest)
        return ingredentsLD.value.toString()
    }

    fun refresh(){
        loadingErrorLD.value = false
        loadingLD.value = true

        queue = Volley.newRequestQueue(getApplication())
        var url = "https://ubaya.fun/hybrid/160419075/listallingredient.php"

        var stringRequest = StringRequest(
            Request.Method.GET,
            url,
            { response ->
                val sType = object : TypeToken<List<Ingredients>>() { }.type
                val result = Gson().fromJson<List<Ingredients>>(response, sType )
                ingredentsLD.value = result

                launch {
                    //            val db = Room.databaseBuilder(getApplication(),
                    //                TodoDatabase::class.java, "tododb").build()
                    val db = buildDB(getApplication())
                    db.recipeDao().insertAllIngredients(result)
                }

                loadingLD.value = false
                Log.d("showvolley", response.toString())

            },
            {
                loadingErrorLD.value = true
                loadingLD.value = false
                Log.d("showvolley", it.toString())
            }
        )
        stringRequest.tag = TAG
        queue?.add(stringRequest)
//        return ingredentsLD.value.toString()
    }

    override fun onCleared() {
        super.onCleared()
        queue?.cancelAll(TAG)
    }

    override val coroutineContext: CoroutineContext
        get() = job + Dispatchers.Main
}