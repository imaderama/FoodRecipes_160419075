package id.ac.ubaya.informatika.foodrecipes_160419075.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import id.ac.ubaya.informatika.foodrecipes_160419075.R
import id.ac.ubaya.informatika.foodrecipes_160419075.databinding.FragmentOptionBinding
import id.ac.ubaya.informatika.foodrecipes_160419075.viewmodel.DetailRecipeViewModel
import kotlinx.android.synthetic.main.fragment_option.*

class OptionFragment : BottomSheetDialogFragment() {
    private lateinit var viewModel: DetailRecipeViewModel
    private lateinit var dataBinding: FragmentOptionBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
//        return inflater.inflate(R.layout.fragment_option, container, false)
        dataBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_option, container, false)
        return dataBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this).get(DetailRecipeViewModel::class.java)

        val id = OptionFragmentArgs.fromBundle(requireArguments()).id

        viewModel.fetch(id)

        observeViewModel()
    }

    fun observeViewModel() {
        viewModel.recipeLD.observe(viewLifecycleOwner, Observer {
            dataBinding.recipe = it
            dataBinding.imageUrl = it.poster
//            imageView2.loadImage(it[0].poster.toString(), progressBar2)
            //TEST
        })
    }
}