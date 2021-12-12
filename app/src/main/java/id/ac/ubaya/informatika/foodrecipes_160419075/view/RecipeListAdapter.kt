package id.ac.ubaya.informatika.foodrecipes_160419075.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import id.ac.ubaya.informatika.foodrecipes_160419075.R
import id.ac.ubaya.informatika.foodrecipes_160419075.databinding.RecipeListItemBinding
import id.ac.ubaya.informatika.foodrecipes_160419075.model.Recipe
import id.ac.ubaya.informatika.foodrecipes_160419075.model.Recipes
import id.ac.ubaya.informatika.foodrecipes_160419075.util.loadImage
import kotlinx.android.synthetic.main.recipe_list_item.view.*

class RecipeListAdapter(val recipeList:ArrayList<Recipes>):RecyclerView.Adapter<RecipeListAdapter.RecipeViewHolder>(), RecipeDetailClickListener, RecipeShareClickListener
     {
    class RecipeViewHolder(val view: RecipeListItemBinding):RecyclerView.ViewHolder(view.root)

    fun updateRecipeList(newRecipeList:List<Recipes>){
        recipeList.clear()
        recipeList.addAll(newRecipeList)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecipeViewHolder {
        val inflater = LayoutInflater.from(parent.context)
//        val v = inflater.inflate(R.layout.recipe_list_item, parent, false)
        val v = DataBindingUtil.inflate<RecipeListItemBinding>(inflater,
            R.layout.recipe_list_item, parent, false)
        return RecipeViewHolder(v)
    }

    override fun onBindViewHolder(holder: RecipeViewHolder, position: Int) {
        with(holder.view){
            recipe =recipeList[position]
            detailsListener = this@RecipeListAdapter
            shareListener = this@RecipeListAdapter
            imageUrl = recipeList[position].poster

//            txtNama.text = recipeList[position].name
//            txtCategory.text = recipeList[position].category
//            imageView.loadImage(recipeList[position].poster.toString(), holder.view.progressBar)
//            txtId.text = recipeList[position].recipe_id.toString()

//            val name = recipeList[position].name
//            val category = recipeList[position].category
//            val like = recipeList[position].likes
//            val poster = recipeList[position].poster
            val id = recipeList[position].recipe_id

//            shareListener = this@RecipeListAdapter
//            detailsListener = this@RecipeListAdapter

//            btnDetails.setOnClickListener {
//
//                val action = RecipeListFragmentDirections.actionRecipeDetail(id!!
//                )
//                Navigation.findNavController(it).navigate(action)
//            }
//
//            btnShare.setOnClickListener {
//                val action = RecipeListFragmentDirections.actionOptionFragment(id!!)
//                Navigation.findNavController(it).navigate(action)
//            }
        }
    }

    override fun getItemCount(): Int {
        return recipeList.size
    }

     override fun onRecipeDetailClick(v: View) {
         val action = RecipeListFragmentDirections.actionRecipeDetail(v.tag.toString().toInt()
         )
         Navigation.findNavController(v).navigate(action)
     }

     override fun onRecipeShareClick(v: View) {
         val action = RecipeListFragmentDirections.actionOptionFragment(v.tag.toString().toInt())
         Navigation.findNavController(v).navigate(action)
     }
}