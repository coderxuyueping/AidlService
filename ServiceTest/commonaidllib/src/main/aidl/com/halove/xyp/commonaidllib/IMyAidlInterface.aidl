// IMyAidlInterface.aidl
package com.halove.xyp.commonaidllib;

// Declare any non-default types here with import statements

import com.halove.xyp.commonaidllib.bean.User;
import com.halove.xyp.commonaidllib.RemoteCallback;

interface IMyAidlInterface {
    /**
     * Demonstrates some basic types that you can use as parameters
     * and return values in AIDL.
     */
    void basicTypes(int anInt, long aLong, boolean aBoolean, float aFloat,
            double aDouble, String aString);

    /**
    *非基本数据类型传输
    * client调用service
    *
    */
    void addUser(in User user);

    List<User> getUsers();


    /**
    *service 调用 client
    *
    */
    void registerCallback(in RemoteCallback callback);
    void unRegisterCallback();
}
