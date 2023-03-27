package com.example.legdaytracker

import android.app.Application

class LegDayApplication : Application() {
    val db by lazy { AppDatabase.getInstance(this) }
}

