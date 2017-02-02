package us.shiroyama.android.pendingintentwithbundleinalarmmanager;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;
import java.util.Locale;
import java.util.Map;
import java.util.TimeZone;

import us.shiroyama.android.pendingintentwithbundleinalarmmanager.receiver.AlarmReceiver;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();
    }

    private void init() {
        final Calendar calendar = Calendar.getInstance(TimeZone.getDefault());
        TimePicker timePicker = (TimePicker) findViewById(R.id.time_picker);
        Log.d(TAG, String.format("hourOfDay: %d, minute: %d", calendar.get(Calendar.HOUR_OF_DAY), calendar.get(Calendar.MINUTE)));
        timePicker.setIs24HourView(true);
        timePicker.setCurrentHour(calendar.get(Calendar.HOUR_OF_DAY));
        timePicker.setCurrentMinute(calendar.get(Calendar.MINUTE));
        timePicker.setOnTimeChangedListener(new TimePicker.OnTimeChangedListener() {
            @Override
            public void onTimeChanged(TimePicker timePicker, int hourOfDay, int minute) {
                Log.d(TAG, String.format("hourOfDay: %d, minute: %d", hourOfDay, minute));
                final Calendar calendar = Calendar.getInstance(TimeZone.getDefault());
                calendar.set(Calendar.HOUR_OF_DAY, hourOfDay);
                calendar.set(Calendar.MINUTE, minute);
                setAlarm(calendar.getTimeInMillis());
                Toast.makeText(MainActivity.this, String.format(Locale.US, "Timer goes off at %02d:%02d", calendar.get(Calendar.HOUR_OF_DAY), calendar.get(Calendar.MINUTE)), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void setAlarm(long timeInMillis) {
        Intent receiver = AlarmReceiver.newIntent(this);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(this, 0, receiver, PendingIntent.FLAG_UPDATE_CURRENT);
        AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
        alarmManager.set(AlarmManager.RTC_WAKEUP, timeInMillis, pendingIntent);
    }
}
