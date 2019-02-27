package io.flamelink.common.types

import com.google.firebase.database.IgnoreExtraProperties

@IgnoreExtraProperties
data class NavigationType(
    var id: String = "",
    var title: String = "",
    var items: List<Items> = emptyList()
)

@IgnoreExtraProperties
data class Items(
    var attachment: Int = 0,
    var component: String = "",
    var cssClass: String = "",
    var id: Long = 0,
    var newWindow: String? = "",
    var order: Int? = 0,
    var parentIndex: Int? = 0,
    var title: String = "",
    var url: String = "",
    var uuid: Long = 0
)