package io.compwiz.countrylister.utils

import android.net.Uri
import android.view.View
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.google.android.material.snackbar.Snackbar
import io.compwiz.countrylister.R

fun View.visible() {
    visibility = View.VISIBLE
}

fun View.gone() {
    visibility = View.GONE
}
fun Fragment.snackBar(message: String, duration: Int = Snackbar.LENGTH_SHORT, callback: () -> Unit) {
    Snackbar.make(requireView(), message, duration)
        .setAction(getString(R.string.retry)) {
            callback()
        }
        .show()
}
fun ImageView.loadImage(url: Uri, isRounded: Boolean = false) {
    val loadingPlaceholder = CircularProgressDrawable(context).apply {
        strokeWidth = 5f
        centerRadius = 20f
    }
    loadingPlaceholder.start()
    val glide = Glide.with(context).load(url)
        .placeholder(loadingPlaceholder)
        .transition(DrawableTransitionOptions.withCrossFade())
        .error(R.drawable.ic_broken_image)
    if (isRounded) glide.transform(CenterCrop(), RoundedCorners(15))
    glide.into(this)
}
