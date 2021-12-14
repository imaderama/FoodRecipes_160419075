package id.ac.ubaya.informatika.foodrecipes_160419075.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import id.ac.ubaya.informatika.foodrecipes_160419075.R
import id.ac.ubaya.informatika.foodrecipes_160419075.model.Grocery
import id.ac.ubaya.informatika.foodrecipes_160419075.util.loadImage
import kotlinx.android.synthetic.main.fragment_create.view.*
import kotlinx.android.synthetic.main.grocery_bag_item.view.*

class CreateAdapter(val groceryList:ArrayList<Grocery>): RecyclerView.Adapter<CreateAdapter.GroceryViewHolder>() {
    class GroceryViewHolder(val view: View): RecyclerView.ViewHolder(view)

//    fun updateBahanList(newGroceryList:List<Grocery>){
//        groceryList.clear()
//        groceryList.addAll(newGroceryList)
//        notifyDataSetChanged()
//    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GroceryViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val v = inflater.inflate(R.layout.grocery_bag_item, parent, false)

        return GroceryViewHolder(v)
    }

    override fun onBindViewHolder(holder: GroceryViewHolder, position: Int) {

    }

    override fun getItemCount(): Int {
        return groceryList.size
    }
}