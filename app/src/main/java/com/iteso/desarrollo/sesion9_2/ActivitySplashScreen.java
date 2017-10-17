package com.iteso.desarrollo.sesion9_2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.Timer;
import java.util.TimerTask;

import beans.User;

public class ActivitySplashScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        // Task para determinar si el usuario está loggeado o no y mandarlo a la actividad correspondiente
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                User user = new User().getUser(ActivitySplashScreen.this);
                Intent intent;

                if(user.isLogged()){
                    intent = new Intent(ActivitySplashScreen.this, ActivityMain.class);
                }else{
                    intent = new Intent(ActivitySplashScreen.this, ActivityLogin.class);
                }

                startActivity(intent);
                finish();
            }
        };

        Timer timer = new Timer();
        timer.schedule(task, 2500); // Espera 2.5s y ejecuta el task
    }
}
