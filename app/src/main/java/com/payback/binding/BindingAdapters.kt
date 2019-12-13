package com.payback.binding

import android.view.View
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.bumptech.glide.Glide

/**
 * Data Binding adapters specific to the app.
 */
object BindingAdapters {
    @JvmStatic
    @BindingAdapter("visibleGone")
    fun showHide(view: View, show: Boolean) {
        view.visibility = if (show) View.VISIBLE else View.GONE
    }

    @JvmStatic
    @BindingAdapter("imageUrl")
    fun showPhoto(imageView: ImageView, url: String) {
        if (url.startsWith("http://"))
            url.replace("http://", "https://")

        val circularProgressDrawable = CircularProgressDrawable(imageView.context)
        circularProgressDrawable.strokeWidth = 5f
        circularProgressDrawable.centerRadius = 80f
        circularProgressDrawable.start()
        Glide.with(imageView.context)
            .load(url)
            .placeholder(circularProgressDrawable)
            .into(imageView)
    }
}