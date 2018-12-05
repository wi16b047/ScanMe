package com.example.android.scanme;

public class AccessPoint extends MainActivity {

    private String MAC;

    private Integer Typ;         //wlan or bluetooth ....

    private Boolean status;

    public AccessPoint(String BSSID, Integer type, boolean status){
        this.MAC = BSSID;
        this.Typ = type;
        this.status = status;
    }

    public String getMAC(){
        return this.MAC;
    }

    public Integer getType(){
        return this.Typ;
    }

    public boolean getStatus(){
        return this.status;
    }
}
