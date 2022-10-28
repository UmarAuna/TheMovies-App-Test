package com.movies.themoviestestapp.utils

import android.util.Log

class Paging {
    // private val logger = Logger(this::class)

    var nextPage = 1
    private var lastPage = 0
    var totalPages = Int.MAX_VALUE

    fun reset() {
        nextPage = 1
        lastPage = 0
    }

    fun performIfNotLastItem(performFetch: () -> Unit) {
        val method = "onLastItemReached"

        Log.i("called. Next page = $nextPage", method)

        if (lastPage == nextPage) {
            Log.d("lastPage is same as nextPage: $lastPage", method)
            return
        }

        if (nextPage < 0) {
            Log.i("This is the last page. Next page = $nextPage", method)
            return
        }

        if (nextPage >= totalPages) {
            Log.i(
                "This is the last page. Next page = $nextPage, Total pages = $totalPages",
                method
            )
            return
        }

        lastPage = nextPage

        performFetch()
    }
}