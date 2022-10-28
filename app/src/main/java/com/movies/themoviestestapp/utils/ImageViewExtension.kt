package com.movies.themoviestestapp.utils

import android.graphics.drawable.Drawable
import android.widget.ImageView
import androidx.annotation.DrawableRes
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
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

fun RecyclerView.setOnLastItemScrolled(onLastItem: () -> Unit) {
    addOnScrollListener(object : RecyclerView.OnScrollListener() {
        override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
            (layoutManager as LinearLayoutManager).apply {
                val visible = childCount
                val total = itemCount
                val past = findFirstVisibleItemPosition()
                if ((visible == total || dy > 0) && visible + past >= total) {
                    onLastItem()
                }
            }
        }
    })
}

inline val RecyclerView.default: RecyclerView
    get() {
        layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        return this
    }
