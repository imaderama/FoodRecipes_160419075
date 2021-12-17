package id.ac.ubaya.informatika.foodrecipes_160419075.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import id.ac.ubaya.informatika.foodrecipes_160419075.R
import id.ac.ubaya.informatika.foodrecipes_160419075.databinding.IngredientListItemBinding
import id.ac.ubaya.informatika.foodrecipes_160419075.databinding.PreparationListItemBinding
import id.ac.ubaya.informatika.foodrecipes_160419075.model.Ingredients
import kotlinx.android.synthetic.main.ingredient_list_item.view.*

class IngredientListAdapter(val ingredientList:ArrayList<Ingredients>): RecyclerView.Adapter<IngredientListAdapter.IngredientViewHolder>() {
    class IngredientViewHolder(var view: IngredientListItemBinding) : RecyclerView.ViewHolder(view.root)

    fun updateIngredientList(newStudentList:List<Ingredients>){
        ingredientList.clear()
        ingredientList.addAll(newStudentList)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): IngredientViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val v = DataBindingUtil.inflate<IngredientListItemBinding>(inflater,
            R.layout.ingredient_list_item, parent, false)

        return IngredientViewHolder(v)
    }

    override fun onBindViewHolder(holder: IngredientViewHolder, position: Int) {
        with(holder.view){
            ingredients = ingredientList[position]
        }
    }

    override fun getItemCount(): Int {
        return ingredientList.size
    }
}