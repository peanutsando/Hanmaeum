package kr.ac.mju.hanmaeum.utils.subway;

import java.util.ArrayList;

/**
 * Created by Youthink on 2017-01-22.
 */

public class realtimeStationArrival {
    private Logging errorMessage;
    private ArrayList<ArrivalInfo> realtimeArrivalList;

    class Logging {
        private String status;
        private String code;
        private String message;
        private String link;
        private String developerMessage;
        private int total;

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }

        public String getDeveloperMessage() {
            return developerMessage;
        }

        public void setDeveloperMessage(String developerMessage) {
            this.developerMessage = developerMessage;
        }

        public String getLink() {
            return link;
        }

        public void setLink(String link) {
            this.link = link;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public int getTotal() {
            return total;
        }

        public void setTotal(int total) {
            this.total = total;
        }
    }

    public Logging getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(Logging errorMessage) {
        this.errorMessage = errorMessage;
    }

    public ArrayList<ArrivalInfo> getRealtimeArrivalList() {
        return realtimeArrivalList;
    }

    public void setRealtimeArrivalList(ArrayList<ArrivalInfo> realtimeArrivalList) {
        this.realtimeArrivalList = realtimeArrivalList;
    }
}
