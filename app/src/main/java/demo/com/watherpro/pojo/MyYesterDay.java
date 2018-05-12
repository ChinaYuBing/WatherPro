package demo.com.watherpro.pojo;

import java.util.Date;

/**
 * Created by Shinelon on 2017/12/5.
 */

public class MyYesterDay {
    private String date;
    private String high;
    private String fx;
    private String low;
    private String fl;
    private String type;

    public String getDate() {
        if (date.length() == 5) {
            return date.substring(2, date.length());
        } else {
            return date.substring(3, date.length());
        }
    }

    Date date2 = new Date();
    int year = date2.getMonth()+1;

    public String getDate1() {
        if (date.length() == 5) {

            return year + "/" + date.substring(0, 1);
        } else {
            return year + "/" + date.substring(0, 2);
        }
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getHigh() {
        return high.substring(2, high.length());
    }

    public void setHigh(String high) {
        this.high = high;
    }

    public String getFx() {
        return fx;
    }

    public void setFx(String fx) {
        this.fx = fx;
    }

    public String getLow() {
        return low.substring(2, low.length());
    }

    public void setLow(String low) {
        this.low = low;
    }

    public String getFl() {
        return fl;
    }

    public void setFl(String fl) {
        this.fl = fl;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
