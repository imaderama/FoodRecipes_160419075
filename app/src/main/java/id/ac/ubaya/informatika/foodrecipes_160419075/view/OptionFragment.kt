package id.ac.ubaya.informatika.foodrecipes_160419075.view

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import id.ac.ubaya.informatika.foodrecipes_160419075.R
import id.ac.ubaya.informatika.foodrecipes_160419075.databinding.FragmentOptionBinding
import id.ac.ubaya.informatika.foodrecipes_160419075.model.Recipes
import id.ac.ubaya.informatika.foodrecipes_160419075.viewmodel.DetailRecipeViewModel
import kotlinx.android.synthetic.main.fragment_option.*

class OptionFragment : BottomSheetDialogFragment(), ButtonShareClickListener {
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
        dataBinding.shareListener = this

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

    override fun onButtonShareClick(v: View) {
        val sendIntent = Intent.createChooser(Intent().apply {
            action = Intent.ACTION_SEND
            var text = "Recipe ${dataBinding.recipe!!.name} is a ${dataBinding.recipe!!.category}" +
                    ", and there are ${dataBinding.recipe!!.likes.toString()} people that like this recipe"
            var poster = dataBinding.recipe!!.poster
            putExtra(Intent.EXTRA_TEXT, text)
            type = "text/plain"
//            putExtra(Intent.EXTRA_STREAM, poster)
//            type = "image/jpeg"
//            `package` = "com.whatsapp"
        },null)
        // Buat share intent
        val shareIntent = Intent.createChooser(sendIntent, "Kirim pesan menggunakan")



//        Log.d("LOOPING", "Ingredient ")
//        val imageUris: ArrayList<String> = arrayListOf(
//            // Add your image URIs here
//
//        )
//
//        val shareIntent = Intent().apply {
//            action = Intent.ACTION_SEND_MULTIPLE
//            putStringArrayListExtra(Intent.EXTRA_STREAM, imageUris)
//            type = "image/*"
//        }
        startActivity(shareIntent)
    }
}