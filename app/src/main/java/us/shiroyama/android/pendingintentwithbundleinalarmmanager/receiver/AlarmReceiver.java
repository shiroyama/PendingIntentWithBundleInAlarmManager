package us.shiroyama.android.pendingintentwithbundleinalarmmanager.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.util.Log;

/**
 * @author Fumihiko Shiroyama
 */

public class AlarmReceiver extends BroadcastReceiver {
    private static final String TAG = AlarmReceiver.class.getSimpleName();

    private static final String RECEIVE_ALARM_ACTION = "us.shiroyama.android.pendingintentwithbundleinalarmmanager.receiver.RECEIVE_ALARM_ACTION";

    public static Intent newIntent(@NonNull Context context) {
        return new Intent(context, AlarmReceiver.class)
                .setAction(RECEIVE_ALARM_ACTION);
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        Log.d(TAG, "AlarmReceiver#onReceive");
    }
}
