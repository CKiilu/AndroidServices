package com.scurrae.chris.androidservices;

import android.app.Service;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;

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
}
