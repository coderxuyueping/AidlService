package com.halove.xyp.aidl_service

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log
import android.widget.Toast

/**
 * Created by xyp on 2018/9/26.
 */
class MyReceiver : BroadcastReceiver() {
    override fun onReceive(p0: Context?, p1: Intent?) {
        Log.d("xuyueping", "service 收到广播")
        Toast.makeText(p0,"service 收到广播",Toast.LENGTH_SHORT).show()
    }
}