package com.example.buttonnav_wifi;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.Formatter;

public class MainActivity extends AppCompatActivity {

    private BottomNavigationView bottomNavigationView;
    private TextView wifi;
    private WifiManager wifimanager;
    private WifiInfo wifiinfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bottomNavigationView = findViewById(R.id.bottom);
        wifi = findViewById(R.id.wifitv);
        wifimanager = (WifiManager)getApplicationContext().getSystemService(Context.WIFI_SERVICE);
        wifiinfo = wifimanager.getConnectionInfo();

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId())
                {
                    case R.id.wifion:
                        if(wifimanager.setWifiEnabled(true))
                        {
                            Toast.makeText(MainActivity.this, "Bekapcsolva", Toast.LENGTH_SHORT).show();
                        }
                        else
                        {
                            Toast.makeText(MainActivity.this, "Hiba", Toast.LENGTH_SHORT).show();
                        }
                        break;
                    case R.id.wifiinfo:
                        ConnectivityManager connectivityManager = ((ConnectivityManager)getSystemService(CONNECTIVITY_SERVICE));
                        NetworkInfo networkInfo = connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
                        if(networkInfo.isConnected())
                        {
                            int ip = wifiinfo.getIpAddress();
                            wifi.setText(android.text.format.Formatter.formatIpAddress(ip));
                        }
                        else
                        {
                            Toast.makeText(MainActivity.this, "Nem csatlakozott", Toast.LENGTH_SHORT).show();
                        }
                        break;
                    case R.id.wifioff:
                        if(wifimanager.setWifiEnabled(false))
                        {
                            Toast.makeText(MainActivity.this, "Kikapcsolva", Toast.LENGTH_SHORT).show();
                        }else
                        {
                            Toast.makeText(MainActivity.this, "Nem lehet kikapcsolni", Toast.LENGTH_SHORT).show();
                        }
                        break;
                }
                return false;
            }
        });
    }
}
