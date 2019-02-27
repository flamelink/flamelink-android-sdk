package io.flamelink.firebase.settings

import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import io.flamelink.common.interfaces.CommonSettings
import io.flamelink.common.interfaces.FlamelinkCallBack
import io.flamelink.common.types.EnvironmentData
import io.flamelink.common.types.GlobalTypes
import io.flamelink.common.types.Globals
import io.flamelink.common.types.Image
import io.flamelink.common.types.ImageTypes

object CacheSettings {
    lateinit var environment: String
    lateinit var locale: String
}

class FirebaseSettings : CommonSettings {

    override fun setEnvironment(environment: String) {
        CacheSettings.environment = environment
    }

    override fun getEnvironment(callBack: FlamelinkCallBack) {
        val environmentVEL = object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                callBack.onAsyncResponse(dataSnapshot.children
                        .map { it.getValue(EnvironmentData::class.java) }
                        .first { it?.id == CacheSettings.environment }?.name)

            }

            override fun onCancelled(databaseError: DatabaseError) {}
        }
        CommonSettings.Companion.environmentRef.addListenerForSingleValueEvent(environmentVEL)
    }

    override fun setLocale(locale: String) {
        CacheSettings.locale = locale
    }

    override fun getLocale(callBack: FlamelinkCallBack) {
        val localVEL = object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                callBack.onAsyncResponse(dataSnapshot.children
                        .map { it.getValue(String::class.java) }
                        .first { it == CacheSettings.locale })
            }

            override fun onCancelled(databaseError: DatabaseError) {}
        }
        CommonSettings.Companion.localeRef.addListenerForSingleValueEvent(localVEL)
    }

    override fun getGlobals(fields: List<GlobalTypes>, callBack: FlamelinkCallBack) {
        val globalVEL = object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                val mapResponse: HashMap<GlobalTypes, Any> = HashMap()
                val globals: Globals? = dataSnapshot.getValue(Globals::class.java)
                globals?.let {
                    it.adminEmail?.let {
                        if (fields.contains(GlobalTypes.ADMIN_EMAIL)) mapResponse[GlobalTypes.ADMIN_EMAIL] = it

                    }

                    it.dateFormat?.let {
                        if (fields.contains(GlobalTypes.DATE_FORMAT)) mapResponse[GlobalTypes.DATE_FORMAT] = it

                    }

                    it.id?.let {
                        if (fields.contains(GlobalTypes.ID)) mapResponse[GlobalTypes.ID] = it
                    }

                    it.siteTitle?.let {
                        if (fields.contains(GlobalTypes.SITE_TITLE)) mapResponse[GlobalTypes.SITE_TITLE] = it
                    }

                    it.tagline?.let {
                        if (fields.contains(GlobalTypes.TAGLINE)) mapResponse[GlobalTypes.TAGLINE] = it
                    }

                    it.timeFormat?.let {
                        if (fields.contains(GlobalTypes.TIME_FORMAT)) mapResponse[GlobalTypes.TIME_FORMAT] = it
                    }

                    it.timeZone?.let {
                        if (fields.contains(GlobalTypes.TIME_ZONE)) mapResponse[GlobalTypes.TIME_ZONE] = it.toString()
                    }
                }
                callBack.onAsyncMapResponse(mapResponse)
            }

            override fun onCancelled(databaseError: DatabaseError) {}
        }
        CommonSettings.Companion.globalRef.addListenerForSingleValueEvent(globalVEL)
    }

    override fun getImageSizes(fields: List<ImageTypes>, callBack: FlamelinkCallBack) {
        val imageSizeVEL = object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                val mapResponse: HashMap<ImageTypes, Any> = HashMap()
                dataSnapshot.children.map { it.getValue(Image::class.java) }.forEach {
                    it?.height?.let {
                        if (fields.contains(ImageTypes.HEIGHT)) mapResponse[ImageTypes.HEIGHT] = it
                    }
                    it?.width?.let {
                        if (fields.contains(ImageTypes.WIDTH)) mapResponse[ImageTypes.WIDTH] = it
                    }
                    it?.quality?.let {
                        if (fields.contains(ImageTypes.QUALITY)) mapResponse[ImageTypes.QUALITY] = it
                    }
                }
                callBack.onAsyncMapResponse(mapResponse)
            }

            override fun onCancelled(databaseError: DatabaseError) {}
        }
        CommonSettings.Companion.imageSizeRef.addListenerForSingleValueEvent(imageSizeVEL)
    }

    override fun getDefaultPermissionsGroup(callBack: FlamelinkCallBack) {
        val permissionGroupVEL = object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                callBack.onAsyncResponse(dataSnapshot.getValue(String::class.java))
            }

            override fun onCancelled(databaseError: DatabaseError) {}
        }
        CommonSettings.Companion.defaultPermissionGroupRef.addListenerForSingleValueEvent(permissionGroupVEL)
    }
}