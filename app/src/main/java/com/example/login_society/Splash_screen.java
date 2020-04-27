package com.example.login_society;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ActionBar;
import android.content.Intent;
import android.os.Bundle;
import android.view.Window;
import android.widget.ImageView;

        public class Splash_screen extends AppCompatActivity {

            @Override
            protected void onCreate(Bundle savedInstanceState) {
                super.onCreate(savedInstanceState);
                requestWindowFeature(Window.FEATURE_NO_TITLE);
                setContentView(R.layout.activity_splash_screen);

                initiate();
                Thread splash = new Thread() {
                    public void run() {
                        try {
                            sleep(3 * 1000);
                            startActivity(new Intent(getBaseContext(), login_form.class));
                            finish();
                        } catch (Exception e) {
                        }
                    }
                };splash.start();
            }
            public void initiate()
            {
                ImageView img=findViewById(R.id.imageView2);
            }
        }