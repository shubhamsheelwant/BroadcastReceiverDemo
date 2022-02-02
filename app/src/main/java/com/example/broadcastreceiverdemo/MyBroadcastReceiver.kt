package com.example.broadcastreceiverdemo

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.telephony.SmsMessage
import android.util.Log
import android.widget.Toast
import android.os.Bundle


class MyBroadcastReceiver : BroadcastReceiver() {
    companion object {
        private const val TAG = "MyBroadcastReceiver"
    }

    override fun onReceive(p0: Context?, p1: Intent?) {
        Log.i(TAG, "Intent recieved: " + p1?.getAction())

        val bundle: Bundle = p1?.extras!!
        if (bundle != null) {
            val pdus = bundle["pdus"] as Array<Any>?
            val messages = arrayOfNulls<SmsMessage>(
                pdus!!.size
            )
            for (i in pdus!!.indices) {
                messages[i] = SmsMessage.createFromPdu(pdus!![i] as ByteArray)
            }
            if (messages.size > -1) {
                Log.i(TAG, "Message recieved: " + messages[0]!!.messageBody)
            }
        }

        Toast.makeText(p0, "Broadcast Receiver triggered", Toast.LENGTH_SHORT).show()
    }
}