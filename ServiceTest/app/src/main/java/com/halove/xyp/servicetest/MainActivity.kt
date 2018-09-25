package com.halove.xyp.servicetest

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.IBinder
import android.view.View

/**
 * service test
 */
class MainActivity : AppCompatActivity() {

    val myIntent  by lazy { Intent(this, MyService::class.java) }

    var binder: MyService.MyBinder? = null

    val MyServiceConnection =
        object : ServiceConnection{
            override fun onServiceDisconnected(p0: ComponentName?) {
                binder = null
            }

            override fun onServiceConnected(p0: ComponentName?, p1: IBinder?) {
                binder = p1 as MyService.MyBinder?
            }
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }


    fun startService(v: View){
        startService(myIntent)
    }

    fun bindService(v: View){
        //隐式启动
        val intent = Intent()
        intent.`package` = packageName
        intent.action = "com.xyp.service"
        bindService(intent, MyServiceConnection, Context.BIND_AUTO_CREATE)
    }

    fun stopService(v: View){
        stopService(myIntent)
    }

    fun unBindService(v: View){
        unbindService(MyServiceConnection)
    }

    fun toast(v: View){
        binder?.getService()?.toast()
    }
}
