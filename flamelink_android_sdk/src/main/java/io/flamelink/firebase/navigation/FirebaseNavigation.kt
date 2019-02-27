package io.flamelink.firebase.navigation

import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import io.flamelink.common.interfaces.CommonNavigation
import io.flamelink.common.interfaces.FlamelinkCallBack
import io.flamelink.common.types.NavigationType
import io.flamelink.firebase.settings.CacheSettings

class FirebaseNavigation : CommonNavigation {

    override fun get(navigationItem: String, callBack: FlamelinkCallBack) {
        val navigationVEL = object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                callBack.onAsyncListResponse(dataSnapshot.children.map {
                    if (navigationItem.isBlank()) {
                        it.child(CacheSettings.locale).getValue(NavigationType::class.java)
                    } else it.getValue(NavigationType::class.java)
                })
            }

            override fun onCancelled(databaseError: DatabaseError) {}
        }

        if (navigationItem.isNotBlank()) {
            CommonNavigation.Companion.navigationRef.environment()
                    .child(navigationItem).addListenerForSingleValueEvent(navigationVEL)
        } else CommonNavigation.Companion.navigationRef.environment()
                .addListenerForSingleValueEvent(navigationVEL)
    }

    override fun getNavigationItems(navigationItem: String, callBack: FlamelinkCallBack) {
        val navigationItemVEL = object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                callBack.onAsyncListResponse(
                        dataSnapshot
                                .child(CacheSettings.locale)
                                .getValue(NavigationType::class.java)?.items.orEmpty()
                )
            }

            override fun onCancelled(databaseError: DatabaseError) {}
        }
        CommonNavigation.Companion.navigationRef.environment().child(navigationItem)
                .addListenerForSingleValueEvent(navigationItemVEL)
    }

}