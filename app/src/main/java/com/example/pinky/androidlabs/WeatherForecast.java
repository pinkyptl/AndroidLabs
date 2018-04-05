package com.example.pinky.androidlabs;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.util.Xml;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.lang.String;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Iterator;

public class WeatherForecast extends Activity {
    ProgressBar progressbar;
    TextView Current_Temparature;
    TextView tv_speed;
    TextView Min_Temparature;
    TextView Max_Temparature;
    ImageView iv_Temparature;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather_forecast);
        String url = "http://api.openweathermap.org/data/2.5/weather?q=ottawa,ca&APPID=d99666875e0e51521f0040a3d97d0f6a&mode=xml&units=metric";
        progressbar = (ProgressBar) findViewById(R.id.progressBar);
        Current_Temparature = findViewById(R.id.current_temparature);
        Min_Temparature = findViewById(R.id.min_temparature);
        Max_Temparature = findViewById(R.id.max_temparature);
        iv_Temparature = findViewById(R.id.iv_temparature);
        tv_speed = findViewById(R.id.speedWind);
        ForecastQuery obj = new ForecastQuery();
        obj.execute(url);
    }

    public class ForecastQuery extends AsyncTask<String, Integer, String> {

        private String speed, min_temp, max_temp, current_temp;
        Bitmap bitmap;

        @Override
        protected String doInBackground(String... strings) {
            try {
                URL url = new URL(strings[0]);
                HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
                InputStream iStream = urlConnection.getInputStream();
                urlConnection.setRequestMethod("GET");
                XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
                factory.setNamespaceAware(false);
                XmlPullParser xpp = factory.newPullParser();
                xpp.setInput(iStream, "UTF-8");
                //Start Reading
                int type;
                while ((type = xpp.getEventType()) != XmlPullParser.END_DOCUMENT) {
                    if (xpp.getEventType() == XmlPullParser.START_TAG) {
                        if (xpp.getName().equals("temperature")) {
                            current_temp = xpp.getAttributeValue(null, "value");
                            Log.i("XML Message:", current_temp);
                            publishProgress(25);

                            min_temp = xpp.getAttributeValue(null, "min");
                            Log.i("XML Message:", min_temp);

                            max_temp = xpp.getAttributeValue(null, "max");
                            Log.i("XML Message:", max_temp);
                            publishProgress(50);
                        }

                        if (xpp.getName().equals("speed")) {
                            speed = xpp.getAttributeValue(null, "value");
                            Log.i("XML Message:", speed);
                            publishProgress(75);
                        }
                        if (xpp.getName().equals("weather")) {
                            String icon = xpp.getAttributeValue(null, "icon");
                            Log.i("XML Message:", icon);
                            publishProgress(100);
                            if (fileExistance(icon + ".png")) {
                                iStream = openFileInput(icon + ".png");
                                bitmap = BitmapFactory.decodeStream(iStream);
                                Log.i("Hiii",iStream.toString());
                            } else {
                                bitmap = getImage(icon + ".png");
                            }
                        }
                    }
                    xpp.next();

                }
                //return (InputStream) parser;
            } catch (XmlPullParserException e) {
                e.printStackTrace();
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return "Done";
        }


        public void opProgressUpdate(Integer... value) {
            progressbar.setVisibility(View.VISIBLE);
            progressbar.setProgress(value[0]);
        }

        public void onPostExecute(String result) {
            Current_Temparature.setText("Current: "+current_temp);
            Min_Temparature.setText("Min: " + min_temp);
            Max_Temparature.setText("Max: "+ max_temp);
            tv_speed.setText("Wind Speed: "+speed);
            iv_Temparature.setImageBitmap(bitmap);
        }

        public Bitmap getImage(String icon) {
            try {
                InputStream is = openFileInput(icon);
                return BitmapFactory.decodeStream(is);
            } catch (FileNotFoundException e) {
                return null;
            }
        }

        public boolean fileExistance(String fname)
        {
            File file = getBaseContext().getFileStreamPath(fname);
            Log.i("File Path", file.getPath().toString());
            return file.exists();
        }
    }
}

