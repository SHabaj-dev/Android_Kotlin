package com.sbz.assignment

import android.Manifest
import android.R
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.content.pm.PackageManager
import android.graphics.Color
import android.os.BatteryManager
import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.telephony.TelephonyManager
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.databinding.DataBindingUtil
import com.sbz.assignment.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val REQUEST_CODE = 101
    private val INTERNET_CONNECTED = "Connected"
    private val INTERNET_DISCONNECTED = "Disconnected"

    var handler: Handler = Handler()
    var runnable: Runnable? = null
    var delay = 10000
    private lateinit var currentBatteryPercentage: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_main)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.tvIMEI.isSelected = true
        binding.tvConStatus.isSelected = true

//        binding.tvIMEI.text = getImeiNumber()
        setConnectionStatus()
//        currentBatteryPercentage = getBatteryLevel()
//        binding.tvBatteryCharge.text = "$currentBatteryPercentage%"
//        binding.tvChargingStatus.text = getBatteryStats()



        val batteryLevelReceiver = object : BroadcastReceiver() {
            override fun onReceive(context: Context, intent: Intent) {
                context.unregisterReceiver(this)
                val level = intent.getIntExtra(BatteryManager.EXTRA_LEVEL, -1)
                val scale = intent.getIntExtra(BatteryManager.EXTRA_SCALE, -1)
                val batteryPct = (level / scale.toFloat()) * 100
                // do something with batteryPct
                binding.tvBatteryCharge.text = batteryPct.toString().trim()
            }
        }

        val filter = IntentFilter(Intent.ACTION_BATTERY_CHANGED)
        registerReceiver(batteryLevelReceiver, filter)

    }

//Requesting Permission
    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        if (requestCode == REQUEST_CODE) {
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(this, "Permission granted.", Toast.LENGTH_SHORT).show()
            } else {
                // in the below line, we are displaying toast message if permissions are not granted.
                Toast.makeText(this, "Permission denied.", Toast.LENGTH_SHORT).show()
            }
        }
    }

    //Function to get The device IMEI number
    private fun getImeiNumber(): String {
        val telephonyManager = getSystemService(TELEPHONY_SERVICE) as TelephonyManager
        if (ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.READ_PHONE_STATE
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(
                this,
                arrayOf(Manifest.permission.READ_PHONE_STATE),
                REQUEST_CODE
            )
        }

        return telephonyManager.imei
    }

//Function to get the Connection status of the deviceBattery
    private fun setConnectionStatus() {
        val networkConnection = NetworkConnection(applicationContext)
        networkConnection.observe(this) { isConnected ->
            if (isConnected) {
                binding.tvConStatus.text = INTERNET_CONNECTED
                binding.tvConStatus.setTextColor(Color.GREEN)
            } else {
                binding.tvConStatus.text = INTERNET_DISCONNECTED
                binding.tvConStatus.setTextColor(Color.RED)
            }
        }
    }

//    private fun getBatteryLevel(): String{
//        val bm = applicationContext.getSystemService(BATTERY_SERVICE) as BatteryManager
//        val batLevel:Int = bm.getIntProperty(BatteryManager.BATTERY_PROPERTY_CAPACITY)
//        return  batLevel.toString()
//    }



}