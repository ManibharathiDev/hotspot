package com.suguna.hotspotapp

import android.net.wifi.WifiManager
import android.net.wifi.WifiManager.LocalOnlyHotspotCallback
import android.net.wifi.WifiManager.LocalOnlyHotspotReservation
import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.widget.Button
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    private lateinit var wifiManager:WifiManager
    private lateinit var reservation: LocalOnlyHotspotReservation
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
       /* val localOnlyHotspotReservation : WifiManager.LocalOnlyHotspotReservation = null
        val localHotspont: Unit = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O)
        {

            WifiManager.LocalOnlyHotspotCallback().onStarted(localOnlyHotspotReservation)

        } else {
            TODO("VERSION.SDK_INT < O")
        }*/

        //setHotspot()
        findViewById<Button>(R.id.enableHotspot).setOnClickListener {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                turnOnHotspot()
            }
        }

        findViewById<Button>(R.id.disableHotspot).setOnClickListener {
            turnOffHotspot()
        }


    }
    @RequiresApi(Build.VERSION_CODES.O)
    private fun turnOnHotspot() {
        val manager = applicationContext.getSystemService(WIFI_SERVICE) as WifiManager
        manager.isWifiEnabled = true
        manager.startLocalOnlyHotspot(object : LocalOnlyHotspotCallback() {
            override fun onStarted(reservation: LocalOnlyHotspotReservation) {
                super.onStarted(reservation)

                this@MainActivity.reservation = reservation
            }

            override fun onStopped() {
                super.onStopped()

            }

            override fun onFailed(reason: Int) {
                super.onFailed(reason)

            }
        }, Handler())
    }

    private fun turnOffHotspot() {
        this@MainActivity.reservation.close()
    }


    /*fun setHotspot(){
        wifiManager = applicationContext.getSystemService(Context.WIFI_SERVICE) as WifiManager
        reservation = LocalOnlyHotspotReservation(this)
        var callback: WifiManager.LocalOnlyHotspotCallback? = null

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            callback = WifiManager.LocalOnlyHotspotCallback()
            callback.onStarted(reservation)
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            wifiManager.startLocalOnlyHotspot(callback, Handler(

            ))
        }
    }*/


}