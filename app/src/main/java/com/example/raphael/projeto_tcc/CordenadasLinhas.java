package com.example.raphael.projeto_tcc;

import java.io.Serializable;

/**
 * Created by raphael on 22/09/15.
 */
public class CordenadasLinhas implements Serializable {

    /**
     * POJO
     */
    private static final long serialVersion = 1L;
    private String lat;
    private String lon;

    public CordenadasLinhas(){

    }


    public String getLat()
    {
        return lat;
    }

    public void setLat(String lat)
    {
        this.lat = lat;
    }

    public String getLon()
    {
        return lon;
    }

    public void setLon(String lon)
    {
        this.lon = lon;
    }

    @Override
    public String toString()
    {
     return lon;
    }

}
