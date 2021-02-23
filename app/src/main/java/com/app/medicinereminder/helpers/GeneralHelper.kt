package com.app.medicinereminder.helpers

import java.text.SimpleDateFormat
import java.util.*

class GeneralHelper {

    companion object {
        fun getTimeFromMilliSeconds(milliseconds: Long?, format: String?): String {
            val date = Date(milliseconds!!)
            val formatter = SimpleDateFormat(format, Locale.getDefault())
            return formatter.format(date)
        }
    }
}