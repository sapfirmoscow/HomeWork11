package ru.sberbank.homework11;

import android.content.ComponentName;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity implements Callback {

    private IDataInterface mDataInterface;
    private ReadFragment mReadFragment;

    private ServiceConnection serviceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            mDataInterface = IDataInterface.Stub.asInterface(service);
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            mDataInterface = null;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initFragments();
    }

    private void initFragments() {
        mReadFragment = ReadFragment.newInstance();

        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction()
                .add(R.id.writer_container,WriteFragment.newInstance(this))
                .add(R.id.reader_container,mReadFragment).commit();

    }

    @Override
    protected void onResume() {
        super.onResume();
        bindService(MyService.newIntent(MainActivity.this),serviceConnection,BIND_AUTO_CREATE);
    }

    @Override
    protected void onPause() {
        super.onPause();
        unbindService(serviceConnection);
    }

    @Override
    public void onUpdate(String value) {
        try {
            mDataInterface.setValue(value);
            mReadFragment.setTextView(value);
        } catch (RemoteException e) {
            Log.e("Exp","CallbackUpdate",e);
        }

    }
}
