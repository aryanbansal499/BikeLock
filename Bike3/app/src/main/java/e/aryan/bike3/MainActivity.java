package e.aryan.bike3;

import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.firebase.client.Firebase;

import static e.aryan.bike3.R.color.locked_green;

public class MainActivity extends AppCompatActivity implements SensorEventListener {


    Button lockUnlockBtn;
    ImageButton lockImage;
    boolean flag = false;
    TextView tv;
    private Sensor mySensor;
    private SensorManager SM;

    View view;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Firebase.setAndroidContext(this);
        //Div awesomeness here on
        SM = (SensorManager) getSystemService(SENSOR_SERVICE);
        mySensor = SM.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        SM.registerListener(this, mySensor, SensorManager.SENSOR_DELAY_NORMAL);

//       bike = new BikeInfo();
         lockUnlockBtn = findViewById(R.id.btn1);
         lockImage = findViewById(R.id.Ibtn);
         tv = findViewById(R.id.tv1);
         lockUnlockBtn.setVisibility(View.INVISIBLE);

         lockImage.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View v) {
                BikeInfo info = new BikeInfo();

                if (flag == false) {
                    info.setLockStatus("LOCKED");
                    lockImage.setImageResource(R.drawable.l1);
                    lockUnlockBtn.setVisibility(View.VISIBLE);
                    tv.setText("Bike is Locked");
                    info.refreshInfo();

                    //v.setBackgroundColor(getResources().getColor(R.color.locked_green));

                    View pv = (View) v.getParent();
                    pv.setBackgroundColor(getResources().getColor(R.color.locked_green));

                    flag = true;


                }
                else{
                    info.setLockStatus("UN-LOCKED");
                    lockImage.setImageResource(R.drawable.l2);
                    tv.setText("Bike is Unlocked");


                    View pv = (View) v.getParent();
                    pv.setBackgroundColor(getResources().getColor(R.color.unlocked));
                    // v.setBackgroundColor(getResources().getColor(R.color.unlocked));
                    lockUnlockBtn.setVisibility(View.INVISIBLE);
                    flag = false;
                }

            }
        });

    }


    @Override
    public void onSensorChanged(SensorEvent event) {
        BikeInfo info = new BikeInfo();
        info.refreshInfo();
        if(BikeInfo.alertStatus != null) {
            if (BikeInfo.alertStatus.equals("PANIC")) {
                setAlertStatus("PANIC");

            } else if (BikeInfo.alertStatus.equals("OK")) {
                setAlertStatus("OK");
            }
        }
        info.finalize();
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }


    public void setAlertStatus(String status) {

        Button stopSiren = findViewById(R.id.button4);


        MediaPlayer siren = MediaPlayer.create(this, R.raw.alarm);
        if(status.equals("PANIC")) {
            view = this.getWindow().getDecorView();
            view.setBackgroundResource(R.color.colorAccent);
            if (BikeInfo.sironStatus.equals("ON")) {
                siren.start();
            }
            stopSiren.setOnClickListener(new View.OnClickListener() {
                MediaPlayer siren = MediaPlayer.create(view.getContext(), R.raw.alarm);
                @Override
                public void onClick(View v) {
                    siren.stop();

                    BikeInfo info = new BikeInfo();
                    info.setAlarm("OFF");

                }

            });


        }
        else if(status.equals("SUSP")) {
            view = this.getWindow().getDecorView();
            //view.setBackgroundResource(R.color.yellow);
            siren.stop();
        }
        else if(status.equals("OK")) {
            view = this.getWindow().getDecorView();
            view.setBackgroundResource(R.color.unlocked);
            siren.stop();
        }
    }
}
