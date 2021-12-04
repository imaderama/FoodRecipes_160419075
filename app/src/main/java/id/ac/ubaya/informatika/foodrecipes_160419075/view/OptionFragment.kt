package id.ac.ubaya.informatika.foodrecipes_160419075.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import id.ac.ubaya.informatika.foodrecipes_160419075.R
import id.ac.ubaya.informatika.foodrecipes_160419075.util.loadImage
import kotlinx.android.synthetic.main.fragment_option.*
import kotlinx.android.synthetic.main.fragment_recipe_detail.*

class OptionFragment : BottomSheetDialogFragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_option, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if(arguments!=null){
//            var name = OptionFragmentArgs.fromBundle(requireArguments()).nama
//            var like = OptionFragmentArgs.fromBundle(requireArguments()).like
//            var poster = OptionFragmentArgs.fromBundle(requireArguments()).poster


//            txtNamaOption.setText(name)
//            txtLikeOption.setText(like.toString() + " people like this recipe")
//            imageView4.loadImage(poster, progressBar7)
        }
    }
}