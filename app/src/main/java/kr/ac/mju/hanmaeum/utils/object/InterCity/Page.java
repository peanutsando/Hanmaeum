package kr.ac.mju.hanmaeum.utils.object.InterCity;

/**
 * Created by Youthink on 2017-02-04.
 */

public class Page {
    private String stationName;
    private String busLocation;

    public Page(String stationName, String busLocation) {
        this.busLocation = busLocation;
        this.stationName = stationName;
    }

    public String getBusLocation() {
        return busLocation;
    }

    public void setBusLocation(String busLocation) {
        this.busLocation = busLocation;
    }

    public String getStationName() {
        return stationName;
    }

    public void setStationName(String stationName) {
        this.stationName = stationName;
    }
}
