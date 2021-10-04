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

class RecipeListAdapter(val recipeList:ArrayList<Recipe>):RecyclerView.Adapter<RecipeListAdapter.RecipeViewHolder>() {
    class RecipeViewHolder(val view: View):RecyclerView.ViewHolder(view)

    fun updateRecipeList(newRecipeList:List<Recipe>){
        recipeList.clear()
        recipeList.addAll(newRecipeList)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecipeViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val v = inflater.inflate(R.layout.recipe_list_item, parent, false)

        return RecipeViewHolder(v)
    }

    override fun onBindViewHolder(holder: RecipeViewHolder, position: Int) {
        with(holder.view){
            txtNama.text = recipeList[position].name
            txtCategory.text = recipeList[position].category
            imageView.loadImage(recipeList[position].poster.toString(), holder.view.progressBar)
            txtId.text = recipeList[position].recipe_id.toString()

            val name = recipeList[position].name
            val category = recipeList[position].category
            val like = recipeList[position].likes
            val poster = recipeList[position].poster
            val id = recipeList[position].recipe_id

            btnDetails.setOnClickListener {

                val action = RecipeListFragmentDirections.actionRecipeDetail(name.toString(), category.toString(), like!!, poster.toString(), id!!
                )
                Navigation.findNavController(it).navigate(action)
            }

            btnShare.setOnClickListener {
                val action = RecipeListFragmentDirections.actionOptionFragment(name.toString(), like!!, poster.toString())
                Navigation.findNavController(it).navigate(action)
            }
        }
    }

    override fun getItemCount(): Int {
        return recipeList.size
    }
}