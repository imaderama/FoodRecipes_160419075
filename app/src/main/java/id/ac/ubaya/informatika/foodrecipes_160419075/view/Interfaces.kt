package id.ac.ubaya.informatika.foodrecipes_160419075.view

import android.content.Context
import android.view.View
import android.widget.ImageView
import android.widget.ProgressBar
//import androidx.databinding.BindingAdapter
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import id.ac.ubaya.informatika.foodrecipes_160419075.R
import id.ac.ubaya.informatika.foodrecipes_160419075.util.loadImage
import java.lang.Exception

interface RecipeDetailClickListener {
    fun onRecipeDetailClick(v: View)
}

interface ImageViewClickListener {
    fun onImageViewClick(v: View)
}

interface RecipeShareClickListener {
    fun onRecipeShareClick(v: View)
}

