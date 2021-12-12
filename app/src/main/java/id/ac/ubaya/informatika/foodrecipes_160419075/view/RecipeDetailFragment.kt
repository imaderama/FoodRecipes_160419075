package id.ac.ubaya.informatika.foodrecipes_160419075.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.squareup.picasso.Picasso
import id.ac.ubaya.informatika.foodrecipes_160419075.R
import id.ac.ubaya.informatika.foodrecipes_160419075.databinding.FragmentRecipeDetailBinding
import id.ac.ubaya.informatika.foodrecipes_160419075.util.loadImage
import id.ac.ubaya.informatika.foodrecipes_160419075.viewmodel.DetailViewModel
import kotlinx.android.synthetic.main.fragment_recipe_detail.*

class RecipeDetailFragment : Fragment() {
    private lateinit var viewModel:DetailViewModel
    private lateinit var dataBinding:FragmentRecipeDetailBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
//        return inflater.inflate(R.layout.fragment_recipe_detail, container, false)
        dataBinding = DataBindingUtil.inflate<FragmentRecipeDetailBinding>(inflater, R.layout.fragment_recipe_detail, container, false)
        return dataBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this).get(DetailViewModel::class.java)

        val id = RecipeDetailFragmentArgs.fromBundle((requireArguments())).id
        viewModel.fetch(id)

        observeViewModel()
//        if(arguments!=null){
//            var name = RecipeDetailFragmentArgs.fromBundle(requireArguments()).name
//            var category = RecipeDetailFragmentArgs.fromBundle(requireArguments()).category
//            var like = RecipeDetailFragmentArgs.fromBundle(requireArguments()).like
//            var poster = RecipeDetailFragmentArgs.fromBundle(requireArguments()).poster
//            var id_recipe = RecipeDetailFragmentArgs.fromBundle(requireArguments()).id
//
//
//            txtNamaDetail.setText(name)
//            txtCategoryDetail.setText(category)
//            txtLike.setText(like.toString())
//            imageView2.loadImage(poster, progressBar2)
//        }
//        Picasso.get().load("https://upload.wikimedia.org/wikipedia/commons/6/6d/Good_Food_Display_-_NCI_Visuals_Online.jpg").into(imageView2)

        btnIngredient.setOnClickListener {
            val action = RecipeDetailFragmentDirections.actionIngredientList(RecipeDetailFragmentArgs.fromBundle(requireArguments()).id)
            Navigation.findNavController(it).navigate(action)
        }

        btnPreparation.setOnClickListener {
            val action = RecipeDetailFragmentDirections.actionPreparationList(RecipeDetailFragmentArgs.fromBundle(requireArguments()).id)
            Navigation.findNavController(it).navigate(action)
        }
    }

    fun observeViewModel() {
        viewModel.recipeLD.observe(viewLifecycleOwner, Observer {
            dataBinding.recipe = it[0]
            dataBinding.imageUrl = it[0].poster.toString()
//            imageView2.loadImage(it[0].poster.toString(), progressBar2)
        })
    }
}