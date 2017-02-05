package kr.ac.mju.hanmaeum.utils.object.subway;

import java.util.ArrayList;

/**
 * Created by Youthink on 2017-02-05.
 */

public class shortestRoute {
    private ErrorMessage errorMessage;
    private ArrayList<ShortestRouteList> shortestRouteList;
    private String message_info_ok;

    public ErrorMessage getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(ErrorMessage errorMessage) {
        this.errorMessage = errorMessage;
    }

    public String getMessage_info_ok() {
        return message_info_ok;
    }

    public void setMessage_info_ok(String message_info_ok) {
        this.message_info_ok = message_info_ok;
    }

    public ArrayList<ShortestRouteList> getShortestRouteList() {
        return shortestRouteList;
    }

    public void setShortestRouteList(ArrayList<ShortestRouteList> shortestRouteList) {
        this.shortestRouteList = shortestRouteList;
    }

    public class ErrorMessage {
        private String total;
        private String message;
        private String status;
        private String developerMessage;
        private String link;
        private String code;

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

        public String getTotal() {
            return total;
        }

        public void setTotal(String total) {
            this.total = total;
        }
    }

    public class ShortestRouteList {
        private String statnFid;
        private String selectedCount;
        private String minStatnCnt;
        private String minStatnNm;
        private String minTransferCnt;
        private String statnTid;
        private String totalCount;
        private String curPage;
        private String shtTravelTm;
        private String statnTnm;
        private String shtTravelMsg;
        private String shtStatnCnt;
        private String minTransferMsg;
        private String statnFnm;
        private String beginRow;
        private String shtStatnNm;
        private String shtTransferMsg;
        private String minTravelMsg;
        private String endRow;
        private String minStatnXy;
        private String shtTransferCnt;
        private String minTravelTm;
        private String rowNum;
        private String shtStatnXy;
        private String pageRow;
        private String shtStatnId;
        private String minStatnId;

        public String getBeginRow() {
            return beginRow;
        }

        public void setBeginRow(String beginRow) {
            this.beginRow = beginRow;
        }

        public String getCurPage() {
            return curPage;
        }

        public void setCurPage(String curPage) {
            this.curPage = curPage;
        }

        public String getEndRow() {
            return endRow;
        }

        public void setEndRow(String endRow) {
            this.endRow = endRow;
        }

        public String getMinStatnCnt() {
            return minStatnCnt;
        }

        public void setMinStatnCnt(String minStatnCnt) {
            this.minStatnCnt = minStatnCnt;
        }

        public String getMinStatnId() {
            return minStatnId;
        }

        public void setMinStatnId(String minStatnId) {
            this.minStatnId = minStatnId;
        }

        public String getMinStatnNm() {
            return minStatnNm;
        }

        public void setMinStatnNm(String minStatnNm) {
            this.minStatnNm = minStatnNm;
        }

        public String getMinStatnXy() {
            return minStatnXy;
        }

        public void setMinStatnXy(String minStatnXy) {
            this.minStatnXy = minStatnXy;
        }

        public String getMinTransferCnt() {
            return minTransferCnt;
        }

        public void setMinTransferCnt(String minTransferCnt) {
            this.minTransferCnt = minTransferCnt;
        }

        public String getMinTransferMsg() {
            return minTransferMsg;
        }

        public void setMinTransferMsg(String minTransferMsg) {
            this.minTransferMsg = minTransferMsg;
        }

        public String getMinTravelMsg() {
            return minTravelMsg;
        }

        public void setMinTravelMsg(String minTravelMsg) {
            this.minTravelMsg = minTravelMsg;
        }

        public String getMinTravelTm() {
            return minTravelTm;
        }

        public void setMinTravelTm(String minTravelTm) {
            this.minTravelTm = minTravelTm;
        }

        public String getPageRow() {
            return pageRow;
        }

        public void setPageRow(String pageRow) {
            this.pageRow = pageRow;
        }

        public String getRowNum() {
            return rowNum;
        }

        public void setRowNum(String rowNum) {
            this.rowNum = rowNum;
        }

        public String getSelectedCount() {
            return selectedCount;
        }

        public void setSelectedCount(String selectedCount) {
            this.selectedCount = selectedCount;
        }

        public String getShtStatnCnt() {
            return shtStatnCnt;
        }

        public void setShtStatnCnt(String shtStatnCnt) {
            this.shtStatnCnt = shtStatnCnt;
        }

        public String getShtStatnId() {
            return shtStatnId;
        }

        public void setShtStatnId(String shtStatnId) {
            this.shtStatnId = shtStatnId;
        }

        public String getShtStatnNm() {
            return shtStatnNm;
        }

        public void setShtStatnNm(String shtStatnNm) {
            this.shtStatnNm = shtStatnNm;
        }

        public String getShtStatnXy() {
            return shtStatnXy;
        }

        public void setShtStatnXy(String shtStatnXy) {
            this.shtStatnXy = shtStatnXy;
        }

        public String getShtTransferCnt() {
            return shtTransferCnt;
        }

        public void setShtTransferCnt(String shtTransferCnt) {
            this.shtTransferCnt = shtTransferCnt;
        }

        public String getShtTransferMsg() {
            return shtTransferMsg;
        }

        public void setShtTransferMsg(String shtTransferMsg) {
            this.shtTransferMsg = shtTransferMsg;
        }

        public String getShtTravelMsg() {
            return shtTravelMsg;
        }

        public void setShtTravelMsg(String shtTravelMsg) {
            this.shtTravelMsg = shtTravelMsg;
        }

        public String getShtTravelTm() {
            return shtTravelTm;
        }

        public void setShtTravelTm(String shtTravelTm) {
            this.shtTravelTm = shtTravelTm;
        }

        public String getStatnFid() {
            return statnFid;
        }

        public void setStatnFid(String statnFid) {
            this.statnFid = statnFid;
        }

        public String getStatnFnm() {
            return statnFnm;
        }

        public void setStatnFnm(String statnFnm) {
            this.statnFnm = statnFnm;
        }

        public String getStatnTid() {
            return statnTid;
        }

        public void setStatnTid(String statnTid) {
            this.statnTid = statnTid;
        }

        public String getStatnTnm() {
            return statnTnm;
        }

        public void setStatnTnm(String statnTnm) {
            this.statnTnm = statnTnm;
        }

        public String getTotalCount() {
            return totalCount;
        }

        public void setTotalCount(String totalCount) {
            this.totalCount = totalCount;
        }
    }
}
