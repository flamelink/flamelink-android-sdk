package io.flamelink.common

import android.content.Context
import com.google.firebase.FirebaseApp

fun Context.init() = FirebaseApp.initializeApp(this)