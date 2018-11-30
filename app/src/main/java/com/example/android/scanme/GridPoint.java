package com.example.android.scanme;

import java.util.List;

public class GridPoint extends MainActivity {

    private String Id;

    private Integer PosX;

    private Integer PosY;

    private List<AccessPoint> APs;

    public GridPoint(String GP_ID,Integer X,Integer Y){
        this.Id = GP_ID;
        this.PosX = X;
        this.PosY = Y;
    }

    public String getGPId(){
        return this.Id;
    }

    public Integer getPosX(){
        return this.PosX;
    }

    public Integer getPosY(){
        return this.PosY;
    }
}
