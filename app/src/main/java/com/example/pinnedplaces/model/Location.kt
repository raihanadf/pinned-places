package com.example.pinnedplaces.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Location (
    val imgPhoto: String,
    val title: String,
    val second: String,
    val support: String,
    val detail: String,
) : Parcelable