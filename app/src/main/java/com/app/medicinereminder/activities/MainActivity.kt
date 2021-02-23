package com.app.medicinereminder.activities

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.TextView
import com.app.medicinereminder.R
import com.app.medicinereminder.helpers.SharePrefHelper
import com.app.medicinereminder.receivers.NotificationReceiver

import kotlinx.android.synthetic.main.activity_main.*
import java.util.*


class MainActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // get selected toggle from shared preference
        val selectToggle=SharePrefHelper(this).selectedToggle
        if(!selectToggle.equals("") && selectToggle.equals(getString(R.string.morning))) {
            selectToggles(tv_toggle_morning)
        }else if(!selectToggle.equals("") && selectToggle.equals(getString(R.string.afternoon))) {
            selectToggles(tv_toggle_afternoon)
        }else if(!selectToggle.equals("") && selectToggle.equals(getString(R.string.evening))) {
            selectToggles(tv_toggle_evening)
        }else if(!selectToggle.equals("") && selectToggle.equals(getString(R.string.night))) {
            selectToggles(tv_toggle_night)
        }
    }

    // Morning toggle click method
    fun onMorningClick(view: View) {
        unSelectToggles()
        selectToggles(tv_toggle_morning)
        scheduleReminder(8)  // 8:00 am in morning
    }

    // Afternoon toggle click method
    fun onAfternoonClick(view: View) {
        unSelectToggles()
        selectToggles(tv_toggle_afternoon)
        scheduleReminder(13)  // 01:00 pm in afternoon
    }

    // Evening toggle click method
    fun onEveningClick(view: View) {
        unSelectToggles()
        selectToggles(tv_toggle_evening)
        scheduleReminder(18) // 06:00 pm in evening
    }

    // Night toggle click method
    fun onNightClick(view: View) {
        unSelectToggles()
        selectToggles(tv_toggle_night)
        scheduleReminder(22) // 10:00 pm in night
    }

    // set select toggle
    private fun selectToggles(toggle : TextView) {
        toggle.setTextColor(getColor(android.R.color.white))
        toggle.setBackgroundResource(R.drawable.bg_toggle_select)
        toggle.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, R.drawable.ic_pills_select) // update toggle end drawable
        SharePrefHelper(this).selectedToggle= toggle.text as String?
    }

    // set as default selected toggle
    private fun unSelectToggles() {
        tv_toggle_morning.setTextColor(getColor(android.R.color.black))
        tv_toggle_morning.setBackgroundResource(R.drawable.bg_toggle_un_select)
        tv_toggle_morning.setCompoundDrawablesWithIntrinsicBounds(0,0,0, R.drawable.ic_pills_un_select)
        tv_toggle_afternoon.setTextColor(getColor(android.R.color.black))
        tv_toggle_afternoon.setBackgroundResource(R.drawable.bg_toggle_un_select)
        tv_toggle_afternoon.setCompoundDrawablesWithIntrinsicBounds(0,0,0, R.drawable.ic_pills_un_select)
        tv_toggle_evening.setTextColor(getColor(android.R.color.black))
        tv_toggle_evening.setBackgroundResource(R.drawable.bg_toggle_un_select)
        tv_toggle_evening.setCompoundDrawablesWithIntrinsicBounds(0,0,0, R.drawable.ic_pills_un_select)
        tv_toggle_night.setTextColor(getColor(android.R.color.black))
        tv_toggle_night.setBackgroundResource(R.drawable.bg_toggle_un_select)
        tv_toggle_night.setCompoundDrawablesWithIntrinsicBounds(0,0,0, R.drawable.ic_pills_un_select)
    }

    // trigger notification alart against switched toggle
    private fun scheduleReminder(hour: Int){
        val calTime=Calendar.getInstance()
        calTime.set(Calendar.HOUR_OF_DAY,hour)
        calTime.set(Calendar.MINUTE,0)
        val intent = Intent(this@MainActivity, NotificationReceiver::class.java)
        val pendingIntent = PendingIntent.getBroadcast(this@MainActivity, 0, intent, 0)
        val alarmManager = getSystemService(Context.ALARM_SERVICE) as AlarmManager
        alarmManager.set(AlarmManager.RTC_WAKEUP,calTime.timeInMillis, pendingIntent)  //schedule alarm
    }

}