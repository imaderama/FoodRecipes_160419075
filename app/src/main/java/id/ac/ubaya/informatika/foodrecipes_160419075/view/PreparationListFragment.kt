package id.ac.ubaya.informatika.foodrecipes_160419075.view

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import id.ac.ubaya.informatika.foodrecipes_160419075.R
import id.ac.ubaya.informatika.foodrecipes_160419075.viewmodel.ListViewModelIngredient
import id.ac.ubaya.informatika.foodrecipes_160419075.viewmodel.ListViewModelPreparation
import kotlinx.android.synthetic.main.fragment_ingredient_list.*
import kotlinx.android.synthetic.main.fragment_preparation_list.*

class PreparationListFragment : Fragment() {
    private  lateinit var viewModel: ListViewModelPreparation
    private val preparationListAdapter = PreparationListAdapter(arrayListOf())
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_preparation_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if(arguments!=null){
//            var id = IngredientListFragmentArgs.fromBundle(requireArguments()).id
        }
        viewModel = ViewModelProvider(this).get(ListViewModelPreparation::class.java)
        viewModel.refresh(PreparationListFragmentArgs.fromBundle(requireArguments()).id.toString())

        Log.d("SHOW ID", IngredientListFragmentArgs.fromBundle(requireArguments()).id.toString())

        recView4.layoutManager = LinearLayoutManager(context)
        recView4.adapter = preparationListAdapter

        observeViewModel()
    }

    fun observeViewModel(){
        viewModel.preparationsLD.observe(viewLifecycleOwner, Observer {
            preparationListAdapter.updatePreparationList(it)
        })

        viewModel.loadingErrorLD.observe(viewLifecycleOwner, Observer {
            if(it){
                textError.visibility = View.VISIBLE
            }
            else{
                textError.visibility = View.GONE
            }
        })

        viewModel.loadingLD.observe(viewLifecycleOwner, Observer {
            if(it){
                progressBar6.visibility = View.VISIBLE
                recView4.visibility = View.GONE
            }
            else{
                progressBar6.visibility = View.GONE
                recView4.visibility = View.VISIBLE
            }
        })
    }
}