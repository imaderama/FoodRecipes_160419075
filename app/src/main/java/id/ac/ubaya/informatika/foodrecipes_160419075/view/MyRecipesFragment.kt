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
import id.ac.ubaya.informatika.foodrecipes_160419075.viewmodel.MyRecipesViewModel
import kotlinx.android.synthetic.main.fragment_my_recipes.*

class MyRecipesFragment : Fragment() {
    private  lateinit var viewModel: MyRecipesViewModel
    private val myRecipeListAdapter = MyRecipesAdapter(arrayListOf())
    private  lateinit var viewModel2: MyRecipesViewModel
    private val myRecipeListAdapter2 = MyRecipesAdapter(arrayListOf())
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_my_recipes, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this).get(MyRecipesViewModel::class.java)
        viewModel.refresh("Breakfast")

        viewModel2 = ViewModelProvider(this).get(MyRecipesViewModel::class.java)
        viewModel2.refresh2("Dessert")

        recView2.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        recView2.adapter = myRecipeListAdapter

        recViewDessert.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        recViewDessert.adapter = myRecipeListAdapter2



        SwipeRefreshLayout.setOnRefreshListener {
            recView2.visibility = View.GONE
            recViewDessert.visibility = View.GONE
            txtErrorBF.visibility = View.GONE
            progressBarBF.visibility = View.VISIBLE
            viewModel.refresh("Breakfast")
            viewModel2.refresh2("Dessert")
            SwipeRefreshLayout.isRefreshing = false
        }

        observeViewModel()

//        txtDessert.visibility = View.GONE
//        recViewDessert.visibility = View.GONE
    }

    fun observeViewModel(){
        viewModel.recipesLD.observe(viewLifecycleOwner, Observer {
            myRecipeListAdapter.updateRecipeList(it)
        })

        viewModel2.recipesLD2.observe(viewLifecycleOwner, Observer {
            myRecipeListAdapter2.updateRecipeList(it)
        })

        viewModel.loadingErrorLD.observe(viewLifecycleOwner, Observer {
            if(it){
                txtErrorBF.visibility = View.VISIBLE
            }
            else{
                txtErrorBF.visibility = View.GONE
            }
        })

        viewModel.loadingLD.observe(viewLifecycleOwner, Observer {
            if(it){
                progressBarBF.visibility = View.VISIBLE
                recView2.visibility = View.GONE
                txtBreakfast.visibility = View.GONE
                txtDessert.visibility = View.GONE
            }
            else{
                progressBarBF.visibility = View.GONE
                recView2.visibility = View.VISIBLE
                txtBreakfast.visibility = View.VISIBLE
                txtDessert.visibility = View.VISIBLE
            }
        })

        viewModel2.loadingErrorLD2.observe(viewLifecycleOwner, Observer {
            if(it){
                txtErrorDS.visibility = View.VISIBLE
            }
            else{
                txtErrorDS.visibility = View.GONE
            }
        })

        viewModel2.loadingLD2.observe(viewLifecycleOwner, Observer {
            if(it){
                progressBarDS.visibility = View.VISIBLE
                recViewDessert.visibility = View.GONE
                txtBreakfast.visibility = View.GONE
                txtDessert.visibility = View.GONE
            }
            else{
                progressBarDS.visibility = View.GONE
                recViewDessert.visibility = View.VISIBLE
                txtBreakfast.visibility = View.VISIBLE
                txtDessert.visibility = View.VISIBLE
            }
        })


    }
}