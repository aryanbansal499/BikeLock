package e.aryan.bike3;

import android.util.Log;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

public class BikeInfo {
    Firebase url;
    public static String name;
    public static String lockStatus;
    public static String alertStatus;
    public static String sironStatus;
    public static double longitude;
    public static double lattitude;

    public BikeInfo(){
        url = new Firebase("https://bikedb-81c06.firebaseio.com");
    }

    public void finalize () {
    }

    public void setName(String name){
        Firebase nameFirebase = url.child("name");
        nameFirebase.setValue(name);
    }

    public void setLockStatus(String lockStat){
        Firebase lockFirebase = url.child("lockStatus");
        lockFirebase.setValue(lockStat);
        BikeInfo.lockStatus = lockStat;
    }

    public void setAlertStatus(String alertStat){

        Firebase alertFirebase = url.child("alertStatus");
        alertFirebase.setValue(alertStat);
        BikeInfo.alertStatus = alertStat;
    }

    public void setAlarm(String sironStatus){
        Firebase sironFirebase = url.child("sironStatus");
        sironFirebase.setValue(sironStatus.toString());
        BikeInfo.sironStatus = sironStatus;
    }

    public void setCoordinates(String lattitude, String longitude){
        Firebase latFirebase = url.child("lat");
        latFirebase.setValue(lattitude);

        Firebase longFirebase = url.child("long");
        longFirebase.setValue(longitude);

        Log.d("DADA", "Coordinates");
    }

    public void refreshInfo(){

        url.child("name").addListenerForSingleValueEvent(new ValueEventListener() {

            public void onDataChange(DataSnapshot snapshot) {
                BikeInfo.name = snapshot.getValue().toString();
                Log.d("Tag", "" + BikeInfo.name);
            }
            @Override
            public void onCancelled(FirebaseError firebaseError) { }
        });

        url.child("alertStatus").addListenerForSingleValueEvent(new ValueEventListener() {

            public void onDataChange(DataSnapshot snapshot) {
                BikeInfo.alertStatus = snapshot.getValue().toString();
                Log.d("Tag", "" + BikeInfo.alertStatus);
            }
            @Override
            public void onCancelled(FirebaseError firebaseError) { }
        });

        url.child("lockStatus").addListenerForSingleValueEvent(new ValueEventListener() {

            public void onDataChange(DataSnapshot snapshot) {
                BikeInfo.lockStatus = snapshot.getValue().toString();
                Log.d("Tag", "" + BikeInfo.lockStatus);
            }
            @Override
            public void onCancelled(FirebaseError firebaseError) { }
        });

        url.child("sironStatus").addListenerForSingleValueEvent(new ValueEventListener() {

            public void onDataChange(DataSnapshot snapshot) {
                BikeInfo.sironStatus = snapshot.getValue().toString();
                Log.d("Tag", "" + BikeInfo.sironStatus);
            }
            @Override
            public void onCancelled(FirebaseError firebaseError) { }
        });

        url.child("long").addListenerForSingleValueEvent(new ValueEventListener() {

            public void onDataChange(DataSnapshot snapshot) {
                BikeInfo.longitude = Double.parseDouble(snapshot.getValue().toString());
                Log.d("Tag", "" + BikeInfo.longitude);
            }
            @Override
            public void onCancelled(FirebaseError firebaseError) { }
        });

        url.child("lat").addListenerForSingleValueEvent(new ValueEventListener() {

            public void onDataChange(DataSnapshot snapshot) {
                BikeInfo.lattitude = Double.parseDouble(snapshot.getValue().toString());
                Log.d("Tag", "" + BikeInfo.lattitude);
            }
            @Override
            public void onCancelled(FirebaseError firebaseError) { }
        });
    }

}