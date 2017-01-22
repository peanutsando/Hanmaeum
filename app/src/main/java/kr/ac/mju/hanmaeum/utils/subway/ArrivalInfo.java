package kr.ac.mju.hanmaeum.utils.subway;

/**
 * Created by Youthink on 2017-01-22.
 */

public class ArrivalInfo {
    private String statnList;
    private String statnFid;
    private int selectedCount;
    private String btrainNo;
    private String subwayList;
    private String statnTid;
    private String subwayId;
    private int totalCount;
    private String curPage;
    private String statnNm;
    private String bstatnNm;
    private String recptnDt;
    private int arvlCd;
    private String trainCo;
    private String beginRow;
    private String bstatnId;
    private String endRow;
    private String barvlDt;
    private String subwayHeading;
    private String btrainSttus;
    private String arvlMsg3;
    private String arvlMsg2;
    private String updnLine;
    private String trainLineNm;
    private String ordkey;
    private int rowNum;
    private String pageRow;
    private String statnId;
    private String subwayNm;

    @Override public String toString() {
        return "ArrivalInfo{" +
                "arvlMsg2='" + arvlMsg2 + '\'' +
                ", arvlMsg3='" + arvlMsg3 + '\'' +
                ", updnLine='" + updnLine + '\'' +
                ", bstatnNm='" + bstatnNm + '\'' +
                ", arvlCd='" + arvlCd + '\'' +
                ", barvlDt='" + barvlDt + '\'' +
                '}';
    }

    public int getArvlCd() {
        return arvlCd;
    }

    public void setArvlCd(int arvlCd) {
        this.arvlCd = arvlCd;
    }

    public String getArvlMsg2() {
        return arvlMsg2;
    }

    public void setArvlMsg2(String arvlMsg2) {
        this.arvlMsg2 = arvlMsg2;
    }

    public String getArvlMsg3() {
        return arvlMsg3;
    }

    public void setArvlMsg3(String arvlMsg3) {
        this.arvlMsg3 = arvlMsg3;
    }

    public String getBarvlDt() {
        return barvlDt;
    }

    public void setBarvlDt(String barvlDt) {
        this.barvlDt = barvlDt;
    }

    public String getBeginRow() {
        return beginRow;
    }

    public void setBeginRow(String beginRow) {
        this.beginRow = beginRow;
    }

    public String getBstatnId() {
        return bstatnId;
    }

    public void setBstatnId(String bstatnId) {
        this.bstatnId = bstatnId;
    }

    public String getBstatnNm() {
        return bstatnNm;
    }

    public void setBstatnNm(String bstatnNm) {
        this.bstatnNm = bstatnNm;
    }

    public String getBtrainNo() {
        return btrainNo;
    }

    public void setBtrainNo(String btrainNo) {
        this.btrainNo = btrainNo;
    }

    public String getBtrainSttus() {
        return btrainSttus;
    }

    public void setBtrainSttus(String btrainSttus) {
        this.btrainSttus = btrainSttus;
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

    public String getOrdkey() {
        return ordkey;
    }

    public void setOrdkey(String ordkey) {
        this.ordkey = ordkey;
    }

    public String getPageRow() {
        return pageRow;
    }

    public void setPageRow(String pageRow) {
        this.pageRow = pageRow;
    }

    public String getRecptnDt() {
        return recptnDt;
    }

    public void setRecptnDt(String recptnDt) {
        this.recptnDt = recptnDt;
    }

    public int getRowNum() {
        return rowNum;
    }

    public void setRowNum(int rowNum) {
        this.rowNum = rowNum;
    }

    public int getSelectedCount() {
        return selectedCount;
    }

    public void setSelectedCount(int selectedCount) {
        this.selectedCount = selectedCount;
    }

    public String getStatnFid() {
        return statnFid;
    }

    public void setStatnFid(String statnFid) {
        this.statnFid = statnFid;
    }

    public String getStatnId() {
        return statnId;
    }

    public void setStatnId(String statnId) {
        this.statnId = statnId;
    }

    public String getStatnList() {
        return statnList;
    }

    public void setStatnList(String statnList) {
        this.statnList = statnList;
    }

    public String getStatnNm() {
        return statnNm;
    }

    public void setStatnNm(String statnNm) {
        this.statnNm = statnNm;
    }

    public String getStatnTid() {
        return statnTid;
    }

    public void setStatnTid(String statnTid) {
        this.statnTid = statnTid;
    }

    public String getSubwayHeading() {
        return subwayHeading;
    }

    public void setSubwayHeading(String subwayHeading) {
        this.subwayHeading = subwayHeading;
    }

    public String getSubwayId() {
        return subwayId;
    }

    public void setSubwayId(String subwayId) {
        this.subwayId = subwayId;
    }

    public String getSubwayList() {
        return subwayList;
    }

    public void setSubwayList(String subwayList) {
        this.subwayList = subwayList;
    }

    public String getSubwayNm() {
        return subwayNm;
    }

    public void setSubwayNm(String subwayNm) {
        this.subwayNm = subwayNm;
    }

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public String getTrainCo() {
        return trainCo;
    }

    public void setTrainCo(String trainCo) {
        this.trainCo = trainCo;
    }

    public String getTrainLineNm() {
        return trainLineNm;
    }

    public void setTrainLineNm(String trainLineNm) {
        this.trainLineNm = trainLineNm;
    }

    public String getUpdnLine() {
        return updnLine;
    }

    public void setUpdnLine(String updnLine) {
        this.updnLine = updnLine;
    }
}
