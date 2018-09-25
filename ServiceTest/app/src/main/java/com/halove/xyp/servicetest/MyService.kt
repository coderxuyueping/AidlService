package com.halove.xyp.servicetest

import android.app.Service
import android.content.Intent
import android.os.Binder
import android.os.IBinder
import android.util.Log
import android.widget.Toast

/**
 * Created by xyp on 2018/9/21.
 * 通过startService启动的，与启动的组件无关，独立运行，通过stopSelf停止
 *    先执行onCreate但是只执行一次，每次调用startService都会执行onStartCommand
 *    stopService回调onDestroy
 * 通过bindService启动的，与组件绑定,之后再调用bindService不再执行onBind
 *    unbindService回调 onUnbind -> onDestroy
 */
class MyService : Service() {

    val TAG = "xuyueping"

    val binder = MyBinder()

    inner class MyBinder : Binder(){
        fun getService(): MyService{
            return this@MyService
        }
    }

    fun toast(){
        Toast.makeText(this, "通过binder调用的服务方法",Toast.LENGTH_SHORT).show()
    }

    override fun onBind(p0: Intent?): IBinder {
        Log.d(TAG, "onBind")
        return binder
    }

    override fun onUnbind(intent: Intent?): Boolean {
        Log.d(TAG, "onUnbind")
        return super.onUnbind(intent)
    }

    override fun onCreate() {
        super.onCreate()
        Log.d(TAG, "onCreate")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG, "onDestroy")
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        //START_STICKY 内存不足，服务被杀死，在空闲时重启，但是Intent为空
        //START_NOT_STICKY 不重启服务
        //START_REDELIVER_INTENT 在运行onStartCommand后service进程被kill后，系统将会再次启动service
        // ，并传入最后一个intent给onStartCommand
        Log.d(TAG, "onStartCommand")
        return START_REDELIVER_INTENT
    }

}