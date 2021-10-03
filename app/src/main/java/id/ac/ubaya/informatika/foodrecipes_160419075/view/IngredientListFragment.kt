package id.ac.ubaya.informatika.foodrecipes_160419075.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import id.ac.ubaya.informatika.foodrecipes_160419075.R
import id.ac.ubaya.informatika.foodrecipes_160419075.viewmodel.ListViewModelIngredient
import kotlinx.android.synthetic.main.fragment_ingredient_list.*
import kotlinx.android.synthetic.main.fragment_recipe_list.*


class IngredientListFragment : Fragment() {
    private  lateinit var viewModel:ListViewModelIngredient
    private val ingredientListAdapter = IngredientListAdapter(arrayListOf())
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_ingredient_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        var id = 0

        if(arguments!=null){
            id = IngredientListFragmentArgs.fromBundle(requireArguments()).id
        }
        viewModel = ViewModelProvider(this).get(ListViewModelIngredient::class.java)
        viewModel.refresh(id)

        recyclerView2.layoutManager = LinearLayoutManager(context)
        recyclerView2.adapter = ingredientListAdapter

        observeViewModel()
    }

    fun observeViewModel(){
        viewModel.ingredentsLD.observe(viewLifecycleOwner, Observer {
            ingredientListAdapter.updateIngredientList(it)
        })

        viewModel.loadingErrorLD.observe(viewLifecycleOwner, Observer {
            if(it){
                txtError3.visibility = View.VISIBLE
            }
            else{
                txtError3.visibility = View.GONE
            }
        })

        viewModel.loadingLD.observe(viewLifecycleOwner, Observer {
            if(it){
                progressBar3.visibility = View.VISIBLE
                recyclerView2.visibility = View.GONE
            }
            else{
                progressBar3.visibility = View.GONE
                recyclerView2.visibility = View.VISIBLE
            }
        })
    }
}