package e.aryan.bike3;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class Main_activity2 {

    @Nullable
    public void onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Log.d("DADA", "hello");
        View view = inflater.inflate(R.layout.activity2, container, false);
        Log.d("DADA", "hello2");

    }
}
