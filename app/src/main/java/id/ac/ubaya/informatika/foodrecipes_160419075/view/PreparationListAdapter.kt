package id.ac.ubaya.informatika.foodrecipes_160419075.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import id.ac.ubaya.informatika.foodrecipes_160419075.R
import id.ac.ubaya.informatika.foodrecipes_160419075.databinding.MyrecipeListItemBinding
import id.ac.ubaya.informatika.foodrecipes_160419075.databinding.PreparationListItemBinding
import id.ac.ubaya.informatika.foodrecipes_160419075.model.Preparations
import kotlinx.android.synthetic.main.ingredient_list_item.view.*
import kotlinx.android.synthetic.main.preparation_list_item.view.*

class PreparationListAdapter(val preparationList:ArrayList<Preparations>): RecyclerView.Adapter<PreparationListAdapter.PreparationViewHolder>() {
    class PreparationViewHolder(var view: PreparationListItemBinding) : RecyclerView.ViewHolder(view.root)

    fun updatePreparationList(newPreparationList:List<Preparations>){
        preparationList.clear()
        preparationList.addAll(newPreparationList)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PreparationViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val v = DataBindingUtil.inflate<PreparationListItemBinding>(inflater,
            R.layout.preparation_list_item, parent, false)
        return PreparationViewHolder(v)
    }

    override fun onBindViewHolder(holder: PreparationViewHolder, position: Int) {
        with(holder.view){
            preparations = preparationList[position]
        }
    }

    override fun getItemCount(): Int {
        return preparationList.size
    }
}