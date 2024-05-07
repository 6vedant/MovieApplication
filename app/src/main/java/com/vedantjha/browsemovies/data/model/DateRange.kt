package com.vedantjha.browsemovies.data.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class DateRange(
    val maximum: String,
    val minimum: String
): Parcelable