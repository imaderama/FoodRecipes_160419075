package id.ac.ubaya.informatika.foodrecipes_160419075.view

import android.app.AlertDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import id.ac.ubaya.informatika.foodrecipes_160419075.R
import id.ac.ubaya.informatika.foodrecipes_160419075.databinding.FragmentCreatePrepAndIngBinding
import id.ac.ubaya.informatika.foodrecipes_160419075.model.Ingredients
import id.ac.ubaya.informatika.foodrecipes_160419075.model.Preparations
import id.ac.ubaya.informatika.foodrecipes_160419075.util.NotificationHelper
import id.ac.ubaya.informatika.foodrecipes_160419075.viewmodel.DetailRecipeViewModel

class CreatePrepAndIngFragment : Fragment(), ButtonAddPIClickListener {
    private lateinit var viewModel: DetailRecipeViewModel
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
        viewModel = ViewModelProvider(this).get(DetailRecipeViewModel::class.java)
        dataBinding.ingredients = Ingredients(0,"","")
        dataBinding.preparations = Preparations(0,0,"")

        dataBinding.listener = this

        if(arguments!=null){
            recipeId = CreatePrepAndIngFragmentArgs.fromBundle(requireArguments()).id
        }
    }

    override fun onButtonAddPIClick(v: View) {
        var result = dataBinding.ingredient!!.split(";").map { it.trim() }
        var result2 = dataBinding.amount!!.split(";").map { it.trim() }
        var result3 = dataBinding.preparation!!.split(";").map { it.trim() }
//        var arrayIng = arrayListOf<Ingredients>()
        AlertDialog.Builder(context).apply {
            setMessage("You want to set public this recipe?")
            setPositiveButton("Yes") { _, _ ->
                if(result.size != result2.size){
                    Toast.makeText(
                        context,
                        "The ingredient and ingredient amount need to be the same amount",
                        Toast.LENGTH_SHORT
                    ).show()
                }
                else {
                    NotificationHelper(v.context).createNotificationPublic("Recipe Uloaded","A new recipe have been public")
                    for ((index, value) in result.withIndex()) {
//                    println("the element at $index is $value")
//                        Log.d("LOOPING", "Ingredient $value amount "+result2[index])
                        viewModel.addIngredient(Ingredients(recipeId, value, result2[index]))
                    }

                    for ((index, value) in result3.withIndex()) {
//                        viewModel.addIngredient(Ingredients(recipeId, value, result2[index]))
                        viewModel.addPreparation(Preparations(recipeId, index+1, value))
                    }
                    viewModel.setPublicRecipe(recipeId)
                    val action = CreatePrepAndIngFragmentDirections.actionBackToMyRecipesFragment()
                    Navigation.findNavController(v).navigate(action)
                }

//                viewModel.addPreparation(Preparations(recipeId, 1, dataBinding.preparation!!))
//                viewModel.addIngredient(Ingredients(recipeId, dataBinding.ingredient!!, dataBinding.amount!!))
//                viewModel.setPublicRecipe(recipeId)
//
//                val action = CreatePrepAndIngFragmentDirections.actionBackToMyRecipesFragment()
//                Navigation.findNavController(v).navigate(action)


//                viewModel.selectLastRecipe()
//                viewModel.deleteAllMyRecipe(4)
//                viewModel.deleteAllMyRecipe(10)
//                Toast.makeText(
//                    context,
//                    dataBinding.recipe!!.toString(),
//                    Toast.LENGTH_SHORT
//                ).show()
            }
            setNegativeButton("Cancel", null)

            create().show()
        }


//        viewModel.deleteIngredient(0)
//        viewModel.deletePreparation(0)
    }
}