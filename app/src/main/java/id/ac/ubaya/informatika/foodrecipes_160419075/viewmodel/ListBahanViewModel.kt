package id.ac.ubaya.informatika.foodrecipes_160419075.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import id.ac.ubaya.informatika.foodrecipes_160419075.model.Grocery
import id.ac.ubaya.informatika.foodrecipes_160419075.model.Recipe

class ListBahanViewModel:ViewModel() {
    val bahanLD = MutableLiveData<List<Grocery>>()
    val loadingErrorLD = MutableLiveData<Boolean>()
    val loadingLD = MutableLiveData<Boolean>()

    fun refresh() {
        val grocery1 = Grocery("Fleischmann's Active Dry Yeast", 20000, 1, "https://m.media-amazon.com/images/I/712EVchF-BL._SL1500_.jpg")
        val grocery2 = Grocery("Eggland's Best Farm Fresh", 60000, 1, "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQcBlNdPsahlFdJpkdWDPlV4cDDIOb0beKrXw&usqp=CAU")
        val grocery3 = Grocery("King Arthur, All Purpose Unbleached Flour", 55000, 1, "https://m.media-amazon.com/images/I/718MjfPfpNL._SY879_.jpg")

        bahanLD.value = arrayListOf<Grocery>(grocery1,grocery2,grocery3)

        loadingErrorLD.value = false
        loadingLD.value = false


    }

//    override fun onCleared() {
//        super.onCleared()
//        queue?.cancelAll(TAG)
//    }
}