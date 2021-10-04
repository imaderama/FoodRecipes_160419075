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
import id.ac.ubaya.informatika.foodrecipes_160419075.viewmodel.ListViewModel
import kotlinx.android.synthetic.main.fragment_my_recipes.*
import kotlinx.android.synthetic.main.fragment_recipe_list.*

class MyRecipesFragment : Fragment() {
    private  lateinit var viewModel: ListViewModel
    private val myRecipeListAdapter = MyRecipesAdapter(arrayListOf())
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_my_recipes, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this).get(ListViewModel::class.java)
        viewModel.refresh()

        recView2.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        recView2.adapter = myRecipeListAdapter

        SwipeRefreshLayout.setOnRefreshListener {
            recView2.visibility = View.GONE
            txtError4.visibility = View.GONE
            progressBar4.visibility = View.VISIBLE
            viewModel.refresh()
            SwipeRefreshLayout.isRefreshing = false
        }

        observeViewModel()
    }

    fun observeViewModel(){
        viewModel.recipesLD.observe(viewLifecycleOwner, Observer {
            myRecipeListAdapter.updateRecipeList(it)
        })

        viewModel.loadingErrorLD.observe(viewLifecycleOwner, Observer {
            if(it){
                txtError4.visibility = View.VISIBLE
            }
            else{
                txtError4.visibility = View.GONE
            }
        })

        viewModel.loadingLD.observe(viewLifecycleOwner, Observer {
            if(it){
                progressBar4.visibility = View.VISIBLE
                recView2.visibility = View.GONE
            }
            else{
                progressBar4.visibility = View.GONE
                recView2.visibility = View.VISIBLE
            }
        })
    }
}