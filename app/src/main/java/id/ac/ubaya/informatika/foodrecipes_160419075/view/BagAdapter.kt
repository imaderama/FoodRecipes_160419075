package id.ac.ubaya.informatika.foodrecipes_160419075.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import id.ac.ubaya.informatika.foodrecipes_160419075.R
import id.ac.ubaya.informatika.foodrecipes_160419075.model.Grocery
import id.ac.ubaya.informatika.foodrecipes_160419075.util.loadImage
import kotlinx.android.synthetic.main.fragment_bag.view.*
import kotlinx.android.synthetic.main.grocery_bag_item.view.*

class BagAdapter(val groceryList:ArrayList<Grocery>): RecyclerView.Adapter<BagAdapter.GroceryViewHolder>() {
    class GroceryViewHolder(val view: View): RecyclerView.ViewHolder(view)

    fun updateBahanList(newGroceryList:List<Grocery>){
        groceryList.clear()
        groceryList.addAll(newGroceryList)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GroceryViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val v = inflater.inflate(R.layout.grocery_bag_item, parent, false)

        return GroceryViewHolder(v)
    }

    override fun onBindViewHolder(holder: GroceryViewHolder, position: Int) {
        with(holder.view){
            textNamaBag.text = groceryList[position].nama
            textHarga.text = groceryList[position].harga.toString()
            imageViewBag.loadImage(groceryList[position].gambar.toString(), holder.view.progressBarBag)
            textJumlah.text = groceryList[position].jumlah.toString()
        }
    }

    override fun getItemCount(): Int {
        return groceryList.size
    }
}