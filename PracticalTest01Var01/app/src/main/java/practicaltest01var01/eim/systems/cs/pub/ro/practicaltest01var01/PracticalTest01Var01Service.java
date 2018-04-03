package practicaltest01var01.eim.systems.cs.pub.ro.practicaltest01var01;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

public class PracticalTest01Var01Service extends Service {
    public PracticalTest01Var01Service() {
    }


    private ProcessingThread processingThread = null;

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        String instr = intent.getStringExtra("res");

        processingThread = new ProcessingThread(this, instr);
        processingThread.start();
        return Service.START_REDELIVER_INTENT;
    }




    @Override
    public void onDestroy() {
        processingThread.stopThread();
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
       return null;
    }
}
