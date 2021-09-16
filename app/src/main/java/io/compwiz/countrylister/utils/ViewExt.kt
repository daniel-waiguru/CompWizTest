/*
 * MIT License
 *
 * Copyright (c) 2021 Daniel Waiguru
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 *
 */

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
fun Fragment.snackbar(message: String, duration: Int = Snackbar.LENGTH_SHORT) {
    Snackbar.make(requireView(), message, duration).show()
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
