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
import id.ac.ubaya.informatika.foodrecipes_160419075.viewmodel.ListBahanViewModel
import kotlinx.android.synthetic.main.fragment_bag.*

class BagFragment : Fragment() {
    private lateinit var viewModel:ListBahanViewModel
    private val bahanListAdapter = BagAdapter(arrayListOf())
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_bag, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this).get(ListBahanViewModel::class.java)
        viewModel.refresh()

        recyclerViewBag.layoutManager = LinearLayoutManager(context)
        recyclerViewBag.adapter = bahanListAdapter

        observeViewModel()
    }

    fun observeViewModel(){
        viewModel.bahanLD.observe(viewLifecycleOwner, Observer {
            bahanListAdapter.updateBahanList(it)
        })

        viewModel.loadingErrorLD.observe(viewLifecycleOwner, Observer {
            if(it){
                textErrorBag.visibility = View.VISIBLE
            }
            else{
                textErrorBag.visibility = View.GONE
            }
        })

        viewModel.loadingLD.observe(viewLifecycleOwner, Observer {
            if(it){
                progressBarBagLoad.visibility = View.VISIBLE
                recyclerViewBag.visibility = View.GONE
            }
            else{
                progressBarBagLoad.visibility = View.GONE
                recyclerViewBag.visibility = View.VISIBLE
            }
        })
    }
}