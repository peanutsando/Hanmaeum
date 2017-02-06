package kr.ac.mju.hanmaeum.utils.object.terminal;

/**
 * Created by Youthink on 2017-02-07.
 */

public class Terminal {
    private String toLocation;
    private String toTime;

    public Terminal(String toLocation, String toTime) {
        this.toLocation = toLocation;
        this.toTime = toTime;
    }

    public String getToLocation() {
        return toLocation;
    }

    public void setToLocation(String toLocation) {
        this.toLocation = toLocation;
    }

    public String getToTime() {
        return toTime;
    }

    public void setToTime(String toTime) {
        this.toTime = toTime;
    }
}
