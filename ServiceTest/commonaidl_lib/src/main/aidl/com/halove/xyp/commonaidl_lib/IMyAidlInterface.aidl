// IMyAidlInterface.aidl
package com.halove.xyp.commonaidl_lib;

// Declare any non-default types here with import statements

import com.halove.xyp.commonaidl_lib.User;

interface IMyAidlInterface {
    /**
     * Demonstrates some basic types that you can use as parameters
     * and return values in AIDL.
     */
    void basicTypes(int anInt, long aLong, boolean aBoolean, float aFloat,
            double aDouble, String aString);

    /**
    *非基本数据类型传输
    *
    */
    void addUser(in User user);

    List<User> getUsers();
}
