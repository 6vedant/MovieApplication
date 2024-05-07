package com.vedantjha.browsemovies.adapter

import android.net.Uri
import android.widget.ImageView
import androidx.core.net.toUri
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import retrofit2.http.Url


@BindingAdapter("bindImage")
fun bindImage(imageView: ImageView, imageUrl: String?) {
    imageUrl?.let {
        Glide.with(imageView.context)
            .load(Uri.parse(imageUrl))
            .into(imageView)
    }
}