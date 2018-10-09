package com.halove.xyp.aidl_client

import android.app.Service
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.IBinder
import android.util.Log
import android.view.View
import com.halove.xyp.commonaidllib.IMyAidlInterface
import com.halove.xyp.commonaidllib.RemoteCallback
import com.halove.xyp.commonaidllib.bean.Name
import com.halove.xyp.commonaidllib.bean.Type
import com.halove.xyp.commonaidllib.bean.User


/**
 * 通过AIDL的方式IPC
 * 客户端
 *
 * AIDL,客户端需要把服务端的aidl文件全部copy过来，包名需要一致，所以把aidl的创建抽出一个公用的lib
 */
class MainActivity : AppCompatActivity() {

    var iMyAidlInterface: IMyAidlInterface? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }


    fun bindService(v: View){
        val isSuccess = bindService(createRemoteServiceIntent(this, "com.xyp.AidlService"), object : ServiceConnection{
            override fun onServiceDisconnected(p0: ComponentName?) {
            }

            override fun onServiceConnected(p0: ComponentName?, p1: IBinder?) {
                iMyAidlInterface = IMyAidlInterface.Stub.asInterface(p1)
            }

        }, Service.BIND_AUTO_CREATE or Service.BIND_ABOVE_CLIENT)
    }

    fun addUser(v: View){
        iMyAidlInterface?.addUser(User("xudaha", Name("name"), Type.BLUE))
    }

    fun getUser(v: View){
        val list = iMyAidlInterface?.users
    }

    fun registerCallback(v: View){
        iMyAidlInterface?.registerCallback(object : RemoteCallback.Stub() {
            override fun sayHello(mess: String?) {
                Log.d("xuyueping",mess)
            }

        })
    }

    fun unRegisterCallback(v: View){
        iMyAidlInterface?.unRegisterCallback()
    }

    fun setType1(v: View){
        iMyAidlInterface?.type = Type.BLUE
    }

    fun getType1(v: View){
        val type = iMyAidlInterface?.type
        Log.d("xuyueping", type?.name)
    }

    //跨进程广播
    fun sendBroadcast(v: View){
        val intent = Intent()
        intent.action = "com.xyp.broadcast"
        //包含停止运行的app
        intent.addFlags(Intent.FLAG_INCLUDE_STOPPED_PACKAGES)
        intent.component = ComponentName("com.halove.xyp.aidl_service","com.halove.xyp.aidl_service.MyReceiver")
        sendBroadcast(intent)
//        startActivity(Intent(this, EmptyActivity::class.java))
    }
















    private fun createRemoteServiceIntent(context: Context, action: String): Intent? {
        val implicitIntent = Intent(action)
        // Retrieve all services that can match the given intent
        val pm = context.packageManager
        val resolveInfo = pm.queryIntentServices(implicitIntent, 0)

        // Make sure only one match was found
        if (resolveInfo == null || resolveInfo.size != 1) {
            return null
        }

        // Get component info and create ComponentName
        val serviceInfo = resolveInfo[0]
        val packageName = serviceInfo.serviceInfo.packageName
        val className = serviceInfo.serviceInfo.name
        val component = ComponentName(packageName, className)

        // Create a new intent. Use the old one for extras and such reuse
        val explicitIntent = Intent(implicitIntent)

        // Set the component to be explicit
        explicitIntent.component = component

        return explicitIntent
    }
}
