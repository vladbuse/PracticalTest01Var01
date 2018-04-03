package practicaltest01var01.eim.systems.cs.pub.ro.practicaltest01var01;

import android.content.Context;
import android.content.Intent;
import android.util.Log;

import java.util.Date;

/**
 * Created by vladb on 4/3/2018.
 */

class ProcessingThread extends Thread
{
    private Context context = null;
    private boolean isRunning = true;
    String instr;


    public ProcessingThread(Context context, String instr) {
        this.context = context;
        this.instr = instr;

    }

    @Override
    public void run() {
        Log.d("[ProcessingThread]", "Thread has started!");
        while (isRunning) {
            sendMessage();
            sleep();
        }
        Log.d("[ProcessingThread]", "Thread has stopped!");
    }

    private void sendMessage() {
        Intent intent = new Intent();
        intent.putExtra("message", new Date(System.currentTimeMillis()) + " " + instr);
        context.sendBroadcast(intent);
    }

    private void sleep() {
        try {
            Thread.sleep(5000);
        } catch (InterruptedException interruptedException) {
            interruptedException.printStackTrace();
        }
    }

    public void stopThread() {
        isRunning = false;
    }
}
