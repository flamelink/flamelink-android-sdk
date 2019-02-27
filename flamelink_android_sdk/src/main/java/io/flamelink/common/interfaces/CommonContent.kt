package io.flamelink.common.interfaces

import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import io.flamelink.firebase.settings.CacheSettings

interface CommonContent{

    companion object {
        private val databaseRef = FirebaseDatabase.getInstance().reference
        val contentRef = databaseRef.child("flamelink").child("environments")
    }

    fun DatabaseReference.environment() = this.child(CacheSettings.environment).child("content")

    fun get(contentItem: String, callBack: FlamelinkCallBack)

    fun getByField(fieldName: String, callBack: FlamelinkCallBack)
}