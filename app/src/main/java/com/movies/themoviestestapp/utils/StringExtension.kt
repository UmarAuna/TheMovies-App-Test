package com.movies.themoviestestapp.utils

import android.content.Context
import android.widget.Toast
import androidx.annotation.StringRes
import androidx.fragment.app.Fragment
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

val LOG_TAG = "com.makeup"

fun <T> T?.toJson(): String {
    return Gson().toJson(this, object : TypeToken<T>() {}.type)
}

fun Fragment.showToast(@StringRes msg: Int) {
    Toast.makeText(requireActivity(), msg, Toast.LENGTH_SHORT).show()
}

fun Fragment.showToast(msg: String) {
    Toast.makeText(requireActivity(), msg, Toast.LENGTH_SHORT).show()
}

fun Context.showToast(msg: String, length: Int = Toast.LENGTH_SHORT) {
    Toast.makeText(this, msg, length).show()
}

inline val String.tagName: String
    get() = "$LOG_TAG.$this"

fun removeExtraTags(content: String): String {
    var content = content

    content = content.replace("<p><p>", "<p>")
    content = content.replace("</p></p>", "</p>")
    content = content.replace("<br>\n</p>", "</p>")
    content = content.replace("\n".toRegex(), " ")
    content = content.replace("<br><br>", "<br> ")
    content = content.replace("<p><br>", "<p> ")
    content = content.replace("<b>", " ")
    content = content.replace("\n", "")
    content = content.replace("</b>", " ")
    content = content.replace("[", " ")
    content = content.replace("]", " ")
    content = content.replace("\"", " ")

    return content
}
