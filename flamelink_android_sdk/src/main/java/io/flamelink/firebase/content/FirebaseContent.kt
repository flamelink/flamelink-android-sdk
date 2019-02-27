package io.flamelink.firebase.content

import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import io.flamelink.common.interfaces.CommonContent
import io.flamelink.common.interfaces.FlamelinkCallBack
import io.flamelink.common.types.ContentType
import io.flamelink.firebase.settings.CacheSettings

class FirebaseContent : CommonContent {

    override fun getByField(fieldName: String, callBack: FlamelinkCallBack) {
        val contentVEL = object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                val listResponse: MutableList<Any?> = emptyList<Any>().toMutableList()
                dataSnapshot.children.map { dsChild ->
                    dsChild.child(CacheSettings.locale).children.map {
                        val uc = it.value
                        val wc: ContentType? = it.getValue(ContentType::class.java)
                        if (fieldName.isNotBlank()) {
                            if (wc?.slug == fieldName) {
                                listResponse.add(uc)
                            }
                        }
                    }
                }
                callBack.onAsyncListResponse(listResponse)
            }

            override fun onCancelled(databaseError: DatabaseError) {}
        }
        CommonContent.contentRef.environment().addListenerForSingleValueEvent(contentVEL)
    }

    override fun get(contentItem: String, callBack: FlamelinkCallBack) {
        val contentVEL = object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                callBack.onAsyncListResponse(dataSnapshot.children.map {
                    if (contentItem.isBlank()) {
                        it.child(CacheSettings.locale).value
                    } else it.value
                })
            }

            override fun onCancelled(databaseError: DatabaseError) {}
        }

        if (contentItem.isNotBlank()) {
            CommonContent.contentRef.environment()
                .child(contentItem).addListenerForSingleValueEvent(contentVEL)
        } else CommonContent.contentRef.environment()
            .addListenerForSingleValueEvent(contentVEL)
    }
}