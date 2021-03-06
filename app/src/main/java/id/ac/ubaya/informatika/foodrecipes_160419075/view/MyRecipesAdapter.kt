package id.ac.ubaya.informatika.foodrecipes_160419075.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import id.ac.ubaya.informatika.foodrecipes_160419075.R
import id.ac.ubaya.informatika.foodrecipes_160419075.databinding.MyrecipeListItemBinding
import id.ac.ubaya.informatika.foodrecipes_160419075.databinding.RecipeListItemBinding
import id.ac.ubaya.informatika.foodrecipes_160419075.model.MyRecipes
import id.ac.ubaya.informatika.foodrecipes_160419075.model.Recipe
import id.ac.ubaya.informatika.foodrecipes_160419075.model.Recipes
import id.ac.ubaya.informatika.foodrecipes_160419075.util.loadImage
import kotlinx.android.synthetic.main.fragment_my_recipes.view.*
import kotlinx.android.synthetic.main.myrecipe_list_item.view.*
import kotlinx.android.synthetic.main.recipe_list_item.view.*

class MyRecipesAdapter(val recipeList:ArrayList<Recipes>): RecyclerView.Adapter<MyRecipesAdapter.MyRecipeViewHolder>(), ButtonEditClickListener, ButtonPIClickListener {
    class MyRecipeViewHolder(val view: MyrecipeListItemBinding): RecyclerView.ViewHolder(view.root)

    fun updateRecipeList(newRecipeList:List<Recipes>){
        recipeList.clear()
        recipeList.addAll(newRecipeList)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyRecipeViewHolder {
        val inflater = LayoutInflater.from(parent.context)
//        val v = inflater.inflate(R.layout.myrecipe_list_item, parent, false)
        val v = DataBindingUtil.inflate<MyrecipeListItemBinding>(inflater,
            R.layout.myrecipe_list_item, parent, false)
        return MyRecipeViewHolder(v)
    }

    override fun onBindViewHolder(holder: MyRecipeViewHolder, position: Int) {
        with(holder.view){
            recipe =recipeList[position]
            editListener = this@MyRecipesAdapter
            addPIListener = this@MyRecipesAdapter
            imageUrl = recipeList[position].poster
            if(recipeList[position].public_stat == 0){
                btnAddIngPrep.visibility = View.VISIBLE
                btnEdit.visibility = View.GONE
            }
//            txtNamaRecipe.text = recipeList[position].name
//            imageView3.loadImage(recipeList[position].poster.toString(), holder.view.progressBar5)


//            imageView3.setOnClickListener {
//                val name = recipeList[position].name
//                val category = recipeList[position].category
//                val like = recipeList[position].likes
//                val poster = recipeList[position].poster
//                val id = recipeList[position].recipe_id
//                val action = MyRecipesFragmentDirections.actionRecipeDetail2(id!!
//                )
//                Navigation.findNavController(it).navigate(action)
//            }
        }
    }

    override fun getItemCount(): Int {
        return recipeList.size
    }

    override fun onButtonEditClick(v: View) {
        val action = MyRecipesFragmentDirections.actionEditFragment(v.tag.toString().toInt()
        )
        Navigation.findNavController(v).navigate(action)
    }

    override fun onButtonPIClick(v: View) {
        val action = MyRecipesFragmentDirections.actionCreatePIFragment(v.tag.toString().toInt()
        )
        Navigation.findNavController(v).navigate(action)
    }

//    override fun onImageViewClick(v: View) {
//        val action = MyRecipesFragmentDirections.actionRecipeDetail2(v.tag.toString().toInt()
//        )
//        Navigation.findNavController(v).navigate(action)
//    }
}