package id.ac.ubaya.informatika.foodrecipes_160419075.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import id.ac.ubaya.informatika.foodrecipes_160419075.R
import id.ac.ubaya.informatika.foodrecipes_160419075.model.Ingredient
import kotlinx.android.synthetic.main.ingredient_list_item.view.*

class IngredientListAdapter(val ingredientList:ArrayList<Ingredient>): RecyclerView.Adapter<IngredientListAdapter.IngredientViewHolder>() {
    class IngredientViewHolder(var view: View) : RecyclerView.ViewHolder(view)

    fun updateIngredientList(newStudentList:List<Ingredient>){
        ingredientList.clear()
        ingredientList.addAll(newStudentList)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): IngredientViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val v = inflater.inflate(R.layout.ingredient_list_item, parent, false)

        return IngredientViewHolder(v)
    }

    override fun onBindViewHolder(holder: IngredientViewHolder, position: Int) {
        with(holder.view){
            txtItem.text = ingredientList[position].item
            txtAmount.text = ingredientList[position].amount

        }
    }

    override fun getItemCount(): Int {
        return ingredientList.size
    }
}