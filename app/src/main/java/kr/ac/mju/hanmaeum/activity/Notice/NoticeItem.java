package kr.ac.mju.hanmaeum.activity.Notice;

/**
 * Created by user on 2017-01-14.
 */

public class NoticeItem {
    public String number;
    public String title;
    public String timestamp;
    public String url;

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public static NoticeItem toNoticeItem(String number, String title, String timestamp, String url) {
        NoticeItem item = new NoticeItem();
        item.setNumber(number);
        item.setTimestamp(timestamp);
        item.setTitle(title);
        item.setUrl(url);

        return item;
    }
}
