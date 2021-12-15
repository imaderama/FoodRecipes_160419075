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
        viewModel.refresh()

        viewModel2 = ViewModelProvider(this).get(MyRecipesViewModel::class.java)
        viewModel2.refresh2()

        recViewMyRecipe.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        recViewMyRecipe.adapter = myRecipeListAdapter

        recViewDraft.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        recViewDraft.adapter = myRecipeListAdapter2



        SwipeRefreshLayout.setOnRefreshListener {
            recViewMyRecipe.visibility = View.GONE
            recViewDraft.visibility = View.GONE
            txtErrorMyRecipe.visibility = View.GONE
            progressBarBF.visibility = View.VISIBLE
            viewModel.refresh()
            viewModel2.refresh2()
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

        viewModel2.recipesDraftLD.observe(viewLifecycleOwner, Observer {
            myRecipeListAdapter2.updateRecipeList(it)
        })

        viewModel.loadingErrorLD.observe(viewLifecycleOwner, Observer {
            if(it){
                txtErrorMyRecipe.visibility = View.VISIBLE
            }
            else{
                txtErrorMyRecipe.visibility = View.GONE
            }
        })

        viewModel.loadingLD.observe(viewLifecycleOwner, Observer {
            if(it){
                progressBarBF.visibility = View.VISIBLE
                recViewMyRecipe.visibility = View.GONE
                txtMyRecipe.visibility = View.GONE
                txtDraft.visibility = View.GONE
            }
            else{
                progressBarBF.visibility = View.GONE
                recViewMyRecipe.visibility = View.VISIBLE
                txtMyRecipe.visibility = View.VISIBLE
                txtDraft.visibility = View.VISIBLE
            }
        })

        viewModel2.loadingErrorDraftLD.observe(viewLifecycleOwner, Observer {
            if(it){
                txtErrorDraft.visibility = View.VISIBLE
            }
            else{
                txtErrorDraft.visibility = View.GONE
            }
        })

        viewModel2.loadingDraftLD.observe(viewLifecycleOwner, Observer {
            if(it){
                progressBarDS.visibility = View.VISIBLE
                txtErrorDraft.visibility = View.GONE
                recViewDraft.visibility = View.GONE
                txtMyRecipe.visibility = View.GONE
                txtDraft.visibility = View.GONE
            }
            else{
                progressBarDS.visibility = View.GONE
                recViewDraft.visibility = View.VISIBLE
                txtMyRecipe.visibility = View.VISIBLE
                txtDraft.visibility = View.VISIBLE
            }
        })


    }
}