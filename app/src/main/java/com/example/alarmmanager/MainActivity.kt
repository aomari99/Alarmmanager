package com.example.alarmmanager

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.icu.text.SimpleDateFormat
import android.os.Build
import android.os.Bundle
import android.widget.Button
import android.widget.TimePicker
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import java.util.*
import kotlin.math.abs


class MainActivity : AppCompatActivity() {

    @RequiresApi(Build.VERSION_CODES.N)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val startbtn = findViewById<Button>(R.id.startalarm)
        val time = findViewById<TimePicker>(R.id.timePicker1)
        time.setIs24HourView(true);
        startbtn.setOnClickListener {



            Toast.makeText(this, "Der Alarm kommt um "+ time.hour.toString() +":"+ getDD(time.minute), Toast.LENGTH_LONG).show()


            val intent = Intent(this, Alarm::class.java)
            val pendingIntent = PendingIntent.getBroadcast(
                    this.applicationContext, 234, intent, 0)
            val alarmManager = getSystemService(Context.ALARM_SERVICE) as AlarmManager
            alarmManager[AlarmManager.RTC_WAKEUP, System.currentTimeMillis() + times(time.hour,time.minute) * 1000] = pendingIntent

       //     Toast.makeText(this, "Der Alarm kommt in ${times(time.hour,time.minute) }  sekuden " , Toast.LENGTH_LONG).show()


        }


    }
    @RequiresApi(Build.VERSION_CODES.N)
    private  fun times (hour : Int, minute : Int) : Long{
        var format = SimpleDateFormat("HH:mm:ss")
        val t1 = format.parse("${hour}:${getDD(minute)}:00")
        val currentTime: String = SimpleDateFormat("HH:mm:ss", Locale.getDefault()).format(Date())

        val t2 = format.parse(currentTime)
       // Toast.makeText(this, "D  ${t1.toString()}  D2  ${t2.toString()}  " , Toast.LENGTH_LONG).show()

        var diff  = abs( t1.time -t2.time)

        val s =   (diff /1000)
        return  s
    }
    private fun getDD(num: Int): String? {
        return if (num > 9) "" + num else "0$num"
    }
}