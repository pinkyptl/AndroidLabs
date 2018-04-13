package com.example.pinky.androidlabs;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class StartActivity extends Activity {
    protected static final String ACTIVITY_NAME = "StartActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
        Log.i(ACTIVITY_NAME,"In onCreate():");
        Button btn2= (Button)findViewById(R.id.button);
        Button tool1= (Button)findViewById(R.id.tool);

        Button btnChat = (Button) findViewById(R.id.button2);
        Button forecastbtn = (Button)findViewById(R.id.weather);
      btn2.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View view) {
              final Intent intent = new Intent(StartActivity.this, ListitemsActivity.class);
              startActivity(intent);
              startActivityForResult(intent,50);
              Log.i(ACTIVITY_NAME, "User clicked Start Chat");
          }
      });

        tool1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Intent intent = new Intent(StartActivity.this, TestToolbar.class);
                startActivity(intent);
            }
        });

      forecastbtn.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View view) {
              final Intent intent = new Intent(StartActivity.this, WeatherForecast.class);
              startActivity(intent);
          }
      });
      btnChat.setOnClickListener(e-> {
          Intent intent = new Intent(StartActivity.this, ChatWindow.class);
          startActivity(intent);
      });
    }  @Override
    protected void onStart() {
        Log.i(ACTIVITY_NAME, "In onStart()");
        super.onStart();
    }

    protected void onResume() {






        Log.i(ACTIVITY_NAME, "In onResume()");
        super.onResume();
    }

    protected void onPause() {
        Log.i(ACTIVITY_NAME, "In onPause()");
        super.onPause();
    }

    protected void onStop() {
        Log.i(ACTIVITY_NAME, "In onStop()");
        super.onStop();

    }

    protected void onDestroy() {
        Log.i(ACTIVITY_NAME, "In onDestroy()");
        super.onDestroy();
    }
//    public void onAcitivyResult(int requestCode,int resultCode,Intent data)
//    {
//        if(resultCode==50)
//        Log.i(ACTIVITY_NAME,"Returned to StartActivity.onActivityResult");
//    }
//    else(resultCode == Activity.RESULT_OK)
//    {
//        String messagePassed  = data.getStringExtra( "Response");
//        Toast toast=Toast.makeText(StartActivity.this,messagePassed,Toast.LENGTH_LONG);
//        toast.show();
//    }
}
