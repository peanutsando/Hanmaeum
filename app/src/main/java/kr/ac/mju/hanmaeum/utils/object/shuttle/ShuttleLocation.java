package kr.ac.mju.hanmaeum.utils.object.shuttle;

/**
 * Created by Youthink on 2017-02-02.
 */

public class ShuttleLocation {
    private double lat;
    private double lon;

    public ShuttleLocation(double lat, double lon) {
        this.lat = lat;
        this.lon = lon;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getLon() {
        return lon;
    }

    public void setLon(double lon) {
        this.lon = lon;
    }
}
