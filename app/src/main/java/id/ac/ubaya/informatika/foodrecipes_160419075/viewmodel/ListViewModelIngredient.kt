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
import id.ac.ubaya.informatika.foodrecipes_160419075.model.Ingredient
import id.ac.ubaya.informatika.foodrecipes_160419075.model.Recipe
import org.json.JSONObject

class ListViewModelIngredient(application: Application): AndroidViewModel(application) {
    val ingredentsLD = MutableLiveData<List<Ingredient>>()
    val loadingErrorLD = MutableLiveData<Boolean>()
    val loadingLD = MutableLiveData<Boolean>()

    private val TAG = "volleyTag"
    private var queue: RequestQueue?= null

    fun refresh(id: String): String{
        loadingErrorLD.value = false
        loadingLD.value = true

        queue = Volley.newRequestQueue(getApplication())
        var url = "http://ubaya.fun/hybrid/160419075/listingredient.php"

//        val stringRequest = object : StringRequest(Request.Method.GET, url,
//                { response ->
//                    val sType = object : TypeToken<List<Ingredient>>() { }.type
//                    val result = Gson().fromJson<List<Ingredient>>(response, sType )
//                    ingredentsLD.value = result
//
//                    loadingLD.value = false
//                    Log.d("showvolley", response.toString())
//
//                },
//                {
//                    loadingErrorLD.value = true
//                    loadingLD.value = false
//                    Log.d("showvolley", it.toString())
//                }
//        ){
//            override fun getParams(): MutableMap<String, String> {
//                val params = HashMap<String, String>()
//                params["id"] = id.toString()
//                return params
//            }
//        }
        var stringRequest = object : StringRequest(
            Request.Method.POST,
            url,
            { response ->
                    val sType = object : TypeToken<List<Ingredient>>() { }.type
                    val result = Gson().fromJson<List<Ingredient>>(response, sType )
                    ingredentsLD.value = result

                    loadingLD.value = false
                    Log.d("showvolley", response.toString())

                },
                {
                    loadingErrorLD.value = true
                    loadingLD.value = false
                    Log.d("showvolley", it.toString())
                }
//            Response.Listener {
////                val sType = object : TypeToken<List<Recipe>>() { }.type
////                val result = Gson().fromJson<List<Recipe>>(response, sType )
////                ingredentsLD.value = result
////
////                loadingLD.value = false
//                val obj = JSONObject(it)
//                val data = obj.getJSONArray("data")
//                if(data.length() > 0){
//                    for(i in 0 until data.length()){
//                        // Ambil JSON Object untuk setiap index
//                        val playObj = data.getJSONObject(i)
//                        val response = ""
//                        // Buat Object Playlist, masukkan data dari JSON Object
//                        with(playObj){
//                            val sType = object : TypeToken<List<Recipe>>() { }.type
//                            val result = Gson().fromJson<List<Recipe>>(sType)
//                            ingredentsLD.value = result
//                            val kode = getString("kode")
//                            val nama = getString("nama")
//                            var sks = getInt("sks")
//                            val nrp = getString("nrp")
//                        }
//                    }
//                }
//            },
//            Response.ErrorListener {
//                Log.e("apiresult", it.toString())
//            }
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

    override fun onCleared() {
        super.onCleared()
        queue?.cancelAll(TAG)
    }
}