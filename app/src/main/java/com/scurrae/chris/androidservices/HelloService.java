package com.scurrae.chris.androidservices;

import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import android.widget.Toast;

/**
 * Created by chris on 3/6/16.
 */
public class HelloService extends Service {
    private Looper mServiceLooper;
    private ServiceHandler mServiceHandler;

    // Handler which receives messages from thread

    private final class ServiceHandler extends Handler{
        public ServiceHandler(Looper looper){
            super(looper);
        }

        @Override
        public void handleMessage(Message msg) {
            // place for a task like, downloading
            try{
                Thread.sleep(5000);
            } catch (InterruptedException e){
                // Restore interrupt status
                Thread.currentThread().interrupt();
            }
            // stop service using startId to avoid stopping
            // in the middle of another job
            stopSelf(msg.arg1);
        }
    }

    @Override
    public void onCreate() {
        // start thread running service
        HandlerThread thread = new HandlerThread("ServiceStartArguments",
                Process.THREAD_PRIORITY_BACKGROUNDS);
        thread.start();
        // Get HandlerThread's looper
        mServiceLooper = thread.getLooper();
        mServiceHandler = new ServiceHandler(mServiceLooper);

        // My toast
        Toast.makeText(this, "Creating service", Toast.LENGTH_SHORT).show();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Toast.makeText(this, "service starting", Toast.LENGTH_SHORT);
        // for each request send a message to start a
        // job and deliver id
        Message msg = mServiceHandler.obtainMessage();
        msg.arg1 = startId;
        mServiceHandler.sendMessage(msg);

        // if killed restart from here
        return START_STICKY;
    }
}