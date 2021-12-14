package id.ac.ubaya.informatika.foodrecipes_160419075.view

import android.app.AlertDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import id.ac.ubaya.informatika.foodrecipes_160419075.R
import id.ac.ubaya.informatika.foodrecipes_160419075.databinding.FragmentCreateBinding
import id.ac.ubaya.informatika.foodrecipes_160419075.model.Ingredients
import id.ac.ubaya.informatika.foodrecipes_160419075.model.MyRecipes
import id.ac.ubaya.informatika.foodrecipes_160419075.viewmodel.DetailRecipeViewModelViewModel
import kotlinx.android.synthetic.main.fragment_create.*

class CreateFragment : Fragment(), ButtonAddClickListener, RadioClickListener {
    private lateinit var viewModel:DetailRecipeViewModelViewModel
    private lateinit var dataBinding:FragmentCreateBinding

    var text = ""

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
//        return inflater.inflate(R.layout.fragment_create, container, false)
        dataBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_create, container, false)
        return dataBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this).get(DetailRecipeViewModelViewModel::class.java)
        dataBinding.myrecipe = MyRecipes("",4,0,"")
        dataBinding.listener = this
        dataBinding.radiolistener = this
//        dataBinding.ingredient = this.toString()
//        dataBinding.ingredients = Ingredients(1,"","")
//        text = dataBinding.ingredients!!.item.toString()
//        observeViewModel()
    }

    override fun onButtonAddClick(v: View) {
//        dataBinding.ingredient = this.
//        observeViewModel()
//        val test = "1.Pertama;2.Kedua;3.Ketiga;4.Keempat"
//        var result = dataBinding.ingredient!!.split(";").map { it.trim() }
//        Toast.makeText(v.context, result.toString(), Toast.LENGTH_SHORT).show()
        AlertDialog.Builder(context).apply {
            setMessage("You want to add this recipe?")
            setPositiveButton("Yes") { _, _ ->
                viewModel.addRecipe(dataBinding.myrecipe!!)
                Toast.makeText(
                    context,
                    dataBinding.myrecipe!!.toString(),
                    Toast.LENGTH_SHORT
                ).show()
            }
            setNegativeButton("Cancel", null)

            create().show()
        }
    }

    override fun onRadioClick(v: View, category: Int, obj: MyRecipes) {
        obj.category = v.tag.toString().toInt()
    }

//    override fun onTextChanged(s: CharSequence) {
//        text = s.toString()
//    }
}