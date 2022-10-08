package com.movies.themoviestestapp.utils

import android.graphics.drawable.Drawable
import android.widget.ImageView
import androidx.annotation.DrawableRes
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestBuilder
import com.bumptech.glide.RequestManager
import com.bumptech.glide.request.RequestOptions
import com.movies.themoviestestapp.R

/*
* Usage
* view.mediaImageView.loadImageWithGlide(url, R.drawable.media_image_placeholder)
* binding.explanationImage.loadImageWithGlide(explanationImageUrl)
* */
fun ImageView.loadImageWithGlide(
    imageUrl: String? = null,
    @DrawableRes placeholder: Int? = null,
    requestOptions: RequestOptions = RequestOptions.fitCenterTransform()
) {
    loadImageWithGlide(imageUrl, placeholder, requestOptions) {
        load(imageUrl)
    }
}

// super function
private fun ImageView.loadImageWithGlide(
    tag: Any?,
    @DrawableRes placeholder: Int? = null,
    requestOptions: RequestOptions = RequestOptions.fitCenterTransform(),
    load: RequestManager.() -> RequestBuilder<Drawable>
) {
    setTag(R.id.glide_tag_key, tag)

    Glide
        .with(this)
        .load()
        .also {
            if (placeholder != null) {
                it.placeholder(placeholder).error(placeholder)
            }
        }
        .apply(requestOptions)
        .into(this)
}
