package io.flamelink.common.types

import com.google.firebase.database.IgnoreExtraProperties

@IgnoreExtraProperties
data class EnvironmentData(
    var id: String? = "",
    var name: String? = ""
)