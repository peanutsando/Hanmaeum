package kr.ac.mju.hanmaeum.utils.object.weather;

import java.util.ArrayList;

/**
 * Created by Youthink on 2017-01-24.
 */

public class Info {
    private String id;
    private String dt;
    private Clouds clouds;
    private Coord coord;
    private Wind wind;
    private String cod;
    private Sys sys;
    private String name;
    private String base;
    private ArrayList<Weather> weather;
    private Main main;

    public class Clouds
    {
        private String all;

        public String getAll ()
        {
            return all;
        }

        public void setAll (String all)
        {
            this.all = all;
        }

    }

    public class Sys{
        private String message;
        private String sunset;
        private String sunrise;
        private String country;

        public String getCountry() {
            return country;
        }

        public void setCountry(String country) {
            this.country = country;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }

        public String getSunrise() {
            return sunrise;
        }

        public void setSunrise(String sunrise) {
            this.sunrise = sunrise;
        }

        public String getSunset() {
            return sunset;
        }

        public void setSunset(String sunset) {
            this.sunset = sunset;
        }
    }
}
