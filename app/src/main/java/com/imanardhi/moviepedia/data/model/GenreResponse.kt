package com.imanardhi.moviepedia.data.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class GenreResponse(
    var id: String,
    var name: String
) : Parcelable
