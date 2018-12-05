package com.example.android.scanme;

import android.Manifest;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private Button ScanMeButton;
    private Spinner spinner;
    private WifiManager wifiManager;
    private ListView listView;
    private int size = 0;
    private List<ScanResult> results;
    private ArrayList<String> arrayList = new ArrayList<>();
    private ArrayAdapter wifi_adapter;

    /*
    Spinner spinner = (Spinner) findViewById(R.id.spinner);
// Create an ArrayAdapter using the string array and a default spinner layout
ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
        R.array.planets_array, android.R.layout.simple_spinner_item);
// Specify the layout to use when the list of choices appears
adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
// Apply the adapter to the spinner
spinner.setAdapter(adapter);

     */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ScanMeButton = (Button) findViewById(R.id.scanner_button);
        ScanMeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                scanWifi();
            }
        });

        listView = findViewById(R.id.wifi_List);
        wifiManager = (WifiManager) getApplicationContext().getSystemService(Context.WIFI_SERVICE);

        if (!wifiManager.isWifiEnabled()) {
            Toast.makeText(this, "WiFi is disabled ... We need to enable it", Toast.LENGTH_LONG).show();
            wifiManager.setWifiEnabled(true);
        }

        wifi_adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, arrayList);
        listView.setAdapter(wifi_adapter);
        scanWifi();

        // CursorAdapter if the choices are available from a database query -> https://developer.android.com/guide/topics/ui/controls/spinner#java
        spinner = (Spinner) findViewById(R.id.location_spinner);
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.location_array, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        spinner.setAdapter(adapter);

    }

    private void scanWifi(){
        arrayList.clear();
        registerReceiver(wifiReceiver, new IntentFilter(WifiManager.SCAN_RESULTS_AVAILABLE_ACTION));
        wifiManager.startScan();
        Toast.makeText(this, "Scanning WiFi ...", Toast.LENGTH_SHORT).show();
     }

    BroadcastReceiver wifiReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            results = wifiManager.getScanResults();
                //https://androidforums.com/threads/wifimanager-getscanresults-always-returns-empty-list.1266068/
            //need to enable permission to access localization service
            for (ScanResult scanResult : results) {
                //if(filterAP(scanResult.BSSID)){
                arrayList.add(scanResult.SSID + " - " + scanResult.BSSID + "    " + scanResult.level + " dBm");
                //}
                wifi_adapter.notifyDataSetChanged();
            }
            unregisterReceiver(this);
        }
    };

    /*
    filter registered APs in database
    private boolean filterAP(String BSSID, List<AccessPoint> registeredAPs){
        //Logic, compare BSSID string to MAC-adresses in our List of registeredAP (get APs from database)
        for(List<AccessPoint> filtered : registeredAPs){
            if(filtered.getMAC().equals(BSSID)){
                return true;
            }
            else {
                return false;
            }
        }      
    }
    */


    //hardcoded method for testing
    private boolean filterAP(String BSSID){

        if (BSSID.equals("1c:e6:c7:1d:6e:34")) {
            return true;
        }
        else {
            return false;
        }
    }

    private void getGridPoints(){
        //service, get Grid Points from database, JSON
    }

    private void getAccessPoints(){
        //service, get Access Points from database, JSON
    }

    private void assignGPToAPs(GridPoint GP, List<AccessPoint> APs){
        //service, send date to database, JSON
    }
}
