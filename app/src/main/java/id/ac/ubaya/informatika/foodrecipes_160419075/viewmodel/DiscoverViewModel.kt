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
import id.ac.ubaya.informatika.foodrecipes_160419075.model.Recipe

class DiscoverViewModel(application: Application): AndroidViewModel(application) {
    val recipesLD = MutableLiveData<List<Recipe>>()
    val loadingErrorLD = MutableLiveData<Boolean>()
    val loadingLD = MutableLiveData<Boolean>()

    private val TAG = "volleyTag"
    private var queue: RequestQueue?= null

    fun refresh(cari: String): String{
        loadingErrorLD.value = false
        loadingLD.value = true

        queue = Volley.newRequestQueue(getApplication())
        var url = "http://ubaya.fun/hybrid/160419075/recipelist3.php"
        var stringRequest = object : StringRequest(
            Request.Method.POST,
            url,
            { response ->
                val sType = object : TypeToken<List<Recipe>>() { }.type
                val result = Gson().fromJson<List<Recipe>>(response, sType )
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
}