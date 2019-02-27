package io.flamelink.common.types

import com.google.firebase.database.IgnoreExtraProperties

@IgnoreExtraProperties
data class ContentType(
    var id: Long = 0,
    var slug: String = "",
    var title: String = "",
    var date: String = ""
)