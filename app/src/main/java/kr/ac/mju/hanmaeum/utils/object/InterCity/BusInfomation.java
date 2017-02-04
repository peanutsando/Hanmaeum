package kr.ac.mju.hanmaeum.utils.object.InterCity;

/**
 * Created by Youthink on 2017-02-05.
 */

public class BusInfomation {
    private String name;
    private String value;

    public BusInfomation(String name, String value) {
        this.name = name;
        this.value = value;
    }

    @Override public String toString() {
        return "name : " + getName() + " value : " + getValue();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
