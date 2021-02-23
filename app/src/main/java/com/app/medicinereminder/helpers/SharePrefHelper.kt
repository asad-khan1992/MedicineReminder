package com.app.medicinereminder.helpers

import android.content.Context
import android.content.SharedPreferences

class SharePrefHelper(context: Context) {

    private val preferences: SharedPreferences =
            context.getSharedPreferences("medicine_reminder_prefs", Context.MODE_PRIVATE)

    var selectedToggle: String?
        get() = preferences.getString("toggle", "")
        set(toggle) {
            val editor = preferences.edit()
            editor.putString("toggle", toggle)
            editor.apply()
            editor.commit()
        }

}