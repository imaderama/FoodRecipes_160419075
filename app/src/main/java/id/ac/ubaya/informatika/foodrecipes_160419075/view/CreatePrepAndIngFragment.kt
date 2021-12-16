package id.ac.ubaya.informatika.foodrecipes_160419075.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import id.ac.ubaya.informatika.foodrecipes_160419075.R
import id.ac.ubaya.informatika.foodrecipes_160419075.databinding.FragmentCreateBinding
import id.ac.ubaya.informatika.foodrecipes_160419075.databinding.FragmentCreatePrepAndIngBinding
import id.ac.ubaya.informatika.foodrecipes_160419075.model.Ingredients
import id.ac.ubaya.informatika.foodrecipes_160419075.viewmodel.CreatePrepAndIngViewModel
import id.ac.ubaya.informatika.foodrecipes_160419075.viewmodel.DetailRecipeViewModel

class CreatePrepAndIngFragment : Fragment(), ButtonAddPIClickListener {
    private lateinit var viewModel: CreatePrepAndIngViewModel
    private lateinit var dataBinding: FragmentCreatePrepAndIngBinding

    var recipeId = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
//        return inflater.inflate(R.layout.fragment_create_prep_and_ing, container, false)
        dataBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_create_prep_and_ing, container, false)
        return dataBinding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this).get(CreatePrepAndIngViewModel::class.java)

        dataBinding.listener = this

        if(arguments!=null){
            recipeId = CreatePrepAndIngFragmentArgs.fromBundle(requireArguments()).id
        }
    }

    override fun onButtonAddPIClick(v: View) {
        var result = dataBinding.ingredient!!.split(";").map { it.trim() }
        var result2 = dataBinding.amount!!.split(";").map { it.trim() }
        var arrayIng = arrayListOf<Ingredients>()
        for (ing in result) {
            for (amount in result2){
                viewModel.addIngredient(Ingredients(recipeId, ing, amount))
//                arrayIng.add(Ingredients(recipeId, ing, amount))
                break
                continue
            }

        }
//        viewModel.addIngredients(arrayIng)
//        dataBinding.ingredients = Ingredients()
//        Toast.makeText(v.context, arrayIng.toString(), Toast.LENGTH_SHORT).show()
    }
}