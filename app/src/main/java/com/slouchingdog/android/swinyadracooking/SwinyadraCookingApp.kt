package com.slouchingdog.android.swinyadracooking

import android.app.Application
import com.slouchingdog.android.swinyadracooking.data.repository.RepositoryImpl

class SwinyadraCookingApp : Application() {
    override fun onCreate() {
        super.onCreate()
        RepositoryImpl.initialize(this)
    }
}