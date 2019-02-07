package ru.sberbank.homework11;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.IBinder;
import android.os.RemoteException;
import android.preference.PreferenceManager;

public class MyService extends Service {

    private SharedPreferences mSharedPreferences;
    public static final String PREFS = "prefs";

    public MyService() {
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mSharedPreferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
    }

    @Override
    public IBinder onBind(Intent intent) {
        return new IDataInterface.Stub() {
            @Override
            public String getValue() throws RemoteException {
                return mSharedPreferences.getString(PREFS,"Error");
            }

            @Override
            public void setValue(String value) throws RemoteException {
                SharedPreferences.Editor editor = mSharedPreferences.edit();
                editor.putString(PREFS,value);
                editor.apply();
            }
        };
    }

    public static Intent newIntent(Context context){
        return new Intent(context,MyService.class);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return START_NOT_STICKY;
    }
}
