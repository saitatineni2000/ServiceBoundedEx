package com.example.saisandeep.serviceboundedex;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;

/**
 * Created by saisandeep on 3/26/2015.
 */
public class BoundService extends Service {

    public final IBinder mBinder=new LocalBinder();

    @Override
    public IBinder onBind(Intent intent) {
        return mBinder;

    }

    public class LocalBinder extends Binder{

        public BoundService getService()
        {
            return BoundService.this;
        }
    }

    public int factorial(int x)
    {
        int fact=1;

        for(int j=1;j<=x;j++)
        {
            fact=fact*j;
        }
        return fact;
    }
}
