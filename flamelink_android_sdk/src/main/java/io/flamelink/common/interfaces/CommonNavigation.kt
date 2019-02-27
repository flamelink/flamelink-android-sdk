package io.flamelink.common.interfaces

import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import io.flamelink.firebase.settings.CacheSettings

interface CommonNavigation {

    companion object {
        private val databaseRef = FirebaseDatabase.getInstance().reference
        val navigationRef = databaseRef.child("flamelink").child("environments")
    }

    fun DatabaseReference.environment() = this.child(CacheSettings.environment).child("navigation")

    fun get(navigationItem: String, callBack: FlamelinkCallBack)

    fun getNavigationItems(navigationItem: String, callBack: FlamelinkCallBack)
}