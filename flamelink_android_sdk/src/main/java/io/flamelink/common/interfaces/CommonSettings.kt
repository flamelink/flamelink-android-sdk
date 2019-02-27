package io.flamelink.common.interfaces

import com.google.firebase.database.FirebaseDatabase
import io.flamelink.common.types.EventTypes
import io.flamelink.common.types.GlobalTypes
import io.flamelink.common.types.ImageTypes

interface CommonSettings {

    companion object {
        private val databaseRef = FirebaseDatabase.getInstance().reference
        val environmentRef = databaseRef.child("flamelink").child("settings").child("environments")
        val localeRef = databaseRef.child("flamelink").child("settings").child("locales")
        val globalRef = databaseRef.child("flamelink").child("settings").child("globals")
        val imageSizeRef = databaseRef.child("flamelink").child("settings").child("general").child("imageSizes")
        val defaultPermissionGroupRef = databaseRef.child("flamelink").child("settings").child("general").child("defaultPermissionsGroup")
    }

    fun setEnvironment(environment: String)

    fun getEnvironment(callBack: FlamelinkCallBack)

    fun setLocale(locale: String)

    fun getLocale(callBack: FlamelinkCallBack)

    fun getGlobals(
        fields: List<GlobalTypes> = emptyList(),
        callBack: FlamelinkCallBack
    )

    fun getImageSizes(fields: List<ImageTypes>, callBack: FlamelinkCallBack)

    fun getDefaultPermissionsGroup(callBack: FlamelinkCallBack)
}