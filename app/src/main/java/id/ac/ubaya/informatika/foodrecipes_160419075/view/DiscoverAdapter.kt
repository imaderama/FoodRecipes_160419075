package id.ac.ubaya.informatika.foodrecipes_160419075.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import id.ac.ubaya.informatika.foodrecipes_160419075.R
import id.ac.ubaya.informatika.foodrecipes_160419075.model.Recipe
import id.ac.ubaya.informatika.foodrecipes_160419075.util.loadImage
import kotlinx.android.synthetic.main.recipe_list_item.view.*
import kotlinx.android.synthetic.main.recipe_listsearch_item.view.*

class DiscoverAdapter(val recipeList:ArrayList<Recipe>): RecyclerView.Adapter<DiscoverAdapter.DiscoverViewHolder>() {
    class DiscoverViewHolder(val view: View): RecyclerView.ViewHolder(view)

    fun updateRecipeList(newRecipeList:List<Recipe>){
        recipeList.clear()
        recipeList.addAll(newRecipeList)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DiscoverViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val v = inflater.inflate(R.layout.recipe_listsearch_item, parent, false)

        return DiscoverViewHolder(v)
    }

    override fun onBindViewHolder(holder: DiscoverViewHolder, position: Int) {
        with(holder.view){
            txtNamad.text = recipeList[position].name
            txtCategoryd.text = recipeList[position].category
            imageViewRecipe.loadImage(recipeList[position].poster.toString(), holder.view.progressBarD)

            val name = recipeList[position].name
            val category = recipeList[position].category
            val like = recipeList[position].likes
            val poster = recipeList[position].poster
            val id = recipeList[position].recipe_id

//            btnDetails.setOnClickListener {
//
//                val action = RecipeListFragmentDirections.actionRecipeDetail(name.toString(), category.toString(), like!!, poster.toString(), id!!
//                )
//                Navigation.findNavController(it).navigate(action)
//            }
//
//            btnShare.setOnClickListener {
//                val action = RecipeListFragmentDirections.actionOptionFragment(name.toString(), like!!, poster.toString())
//                Navigation.findNavController(it).navigate(action)
//            }
        }
    }

    override fun getItemCount(): Int {
        return recipeList.size
    }
}