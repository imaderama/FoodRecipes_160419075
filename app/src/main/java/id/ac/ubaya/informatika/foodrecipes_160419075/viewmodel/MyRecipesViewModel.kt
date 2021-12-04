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
import id.ac.ubaya.informatika.foodrecipes_160419075.model.Preparation
import id.ac.ubaya.informatika.foodrecipes_160419075.model.Recipe
import id.ac.ubaya.informatika.foodrecipes_160419075.model.Recipes

class MyRecipesViewModel(application: Application): AndroidViewModel(application) {
    val recipesLD = MutableLiveData<List<Recipes>>()
    val loadingErrorLD = MutableLiveData<Boolean>()
    val loadingLD = MutableLiveData<Boolean>()
    val recipesLD2 = MutableLiveData<List<Recipes>>()
    val loadingErrorLD2 = MutableLiveData<Boolean>()
    val loadingLD2 = MutableLiveData<Boolean>()

    private val TAG = "volleyTag"
    private var queue: RequestQueue?= null

    fun refresh(category: String): String{
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
    }

    fun refresh2(category: String): String{
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
                    recipesLD2.value = result

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
        return recipesLD2.value.toString()
    }

    override fun onCleared() {
        super.onCleared()
        queue?.cancelAll(TAG)
    }
}