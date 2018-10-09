package com.halove.xyp.aidl_service

import android.app.Service
import android.content.Intent
import android.os.IBinder
import android.util.Log
import com.halove.xyp.commonaidllib.RemoteCallback
import com.halove.xyp.commonaidllib.IMyAidlInterface
import com.halove.xyp.commonaidllib.bean.Type
import com.halove.xyp.commonaidllib.bean.User


/**
 * Created by xyp on 2018/9/21.
 */
class AidlService : Service() {
    val userList by lazy { mutableListOf<User>() }

    var type1: Type? =  null

    var remoteCallback: RemoteCallback? = null

    override fun onBind(p0: Intent?): IBinder {
        return myBinder
    }

    private val myBinder = object : IMyAidlInterface.Stub(){
        override fun setType(type: Type?) {
            type1 = type!!
        }

        override fun getType(): Type {
            return type1!!
        }

        /**
         * service 调用 client
         *
         */
        override fun registerCallback(callback: RemoteCallback?) {
            remoteCallback = callback
            //服务端向客户端发送消息
            remoteCallback?.sayHello("你好，客户端!")
            Log.d("xuyueping", "registerCallback")
        }

        override fun unRegisterCallback() {
            remoteCallback = null
            Log.d("xuyueping", "unRegisterCallback")
        }


        /**
         * 非基本数据类型传输
         */

        override fun addUser(user: User?) {
            userList.add(user!!)
        }

        override fun getUsers(): MutableList<User> {
            return userList
        }

        /**
         * 基本数据类型可以直接传输，但是其他的需要经过处理
          */
        override fun basicTypes(anInt: Int, aLong: Long, aBoolean: Boolean, aFloat: Float, aDouble: Double, aString: String?) {
            val a = anInt
        }
    }
}