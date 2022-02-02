package com.example.broadcastreceiverdemo

import android.Manifest
import android.content.Intent
import android.content.IntentFilter
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import androidx.core.app.ActivityCompat
import androidx.databinding.DataBindingUtil
import com.example.broadcastreceiverdemo.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private val TAG = "MainActivity"
    lateinit var binding: ActivityMainBinding
    lateinit var broadcastReceiver: MyBroadcastReceiver
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        initPermissions()
        broadcastReceiver = MyBroadcastReceiver()

        binding.btnStart.setOnClickListener {
            Log.i(TAG, "btnStart clicked ")
            val intent = Intent()
            intent.action = "android.provider.Telephony.SMS_RECEIVED"
            sendBroadcast(intent)
        }
    }

    private fun initPermissions() {
        ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.RECEIVE_SMS), 1);
        ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.READ_SMS), 1);
    }

    override fun onStart() {
        super.onStart()
        val intentFilter = IntentFilter("android.provider.Telephony.SMS_RECEIVED")
        registerReceiver(broadcastReceiver, intentFilter)
    }


    override fun onStop() {
        super.onStop()
        unregisterReceiver(broadcastReceiver)
    }
}