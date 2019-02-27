package io.flamelink.common.interfaces

import io.flamelink.common.types.EventTypes
import io.flamelink.common.types.GlobalTypes
import io.flamelink.common.types.ImageTypes

interface Settings {

    fun setEnvironment(environment: String)

    fun getEnvironment(): String

    fun setLocale(locale: String)

    fun getLocale(): String

    fun getGlobals(
        fields: List<GlobalTypes>? = null,
        events: List<EventTypes> = listOf(EventTypes.VALUE)
    )

    fun getImageSizes(fields: List<ImageTypes>? = null): String

    fun getDefaultPermissionsGroup(): String
}