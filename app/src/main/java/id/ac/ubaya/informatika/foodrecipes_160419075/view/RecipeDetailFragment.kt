package id.ac.ubaya.informatika.foodrecipes_160419075.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import id.ac.ubaya.informatika.foodrecipes_160419075.R
import id.ac.ubaya.informatika.foodrecipes_160419075.util.loadImage
import kotlinx.android.synthetic.main.fragment_recipe_detail.*

class RecipeDetailFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_recipe_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if(arguments!=null){
            var name = RecipeDetailFragmentArgs.fromBundle(requireArguments()).name
            var category = RecipeDetailFragmentArgs.fromBundle(requireArguments()).category
            var like = RecipeDetailFragmentArgs.fromBundle(requireArguments()).like
            var poster = RecipeDetailFragmentArgs.fromBundle(requireArguments()).poster


            txtNamaDetail.setText(name)
            txtCategoryDetail.setText(category)
            txtLike.setText(like.toString())
            imageView2.loadImage(poster, progressBar2)
        }
    }
}