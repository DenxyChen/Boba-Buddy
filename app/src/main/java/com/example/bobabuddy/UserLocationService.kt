package com.example.bobabuddy

import android.app.Service
import android.content.Intent
import android.os.IBinder

class UserLocationService : Service() {
    override fun onBind(intent: Intent): IBinder? {
        return null
    }
}