package com.example;

import java.util.HashMap;
import java.util.concurrent.ConcurrentHashMap;


public class SafeContactManager
{
    private static SafeContactManager mThisInstance = null;
    private String mCurrentPhoneNumber = null;

    public static SafeContactManager GetInstance(String aNumber)
    {
        if (mThisInstance == null)
            mThisInstance = new SafeContactManager(aNumber);
        return mThisInstance;
    }

    private SafeContactManager(String aNumber)
    {
        mCurrentPhoneNumber = aNumber;
    }
}
