package com.halove.xyp.aidl_client

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Toast

/**
 * Created by xyp on 2018/9/26.
 */
class EmptyActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Toast.makeText(this, "empty", Toast.LENGTH_SHORT).show()
    }
}