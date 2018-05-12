package demo.com.watherpro.pojo;

import android.util.Log;

import java.util.Date;

/**
 * Created by Shinelon on 2017/12/5.
 */

public class MyForeCast {

    private String date;
    private String high;
    private String fengLi;
    private String low;
    private String fengXiang;
    private String type;

    public String getDate() {
        if (date.length() == 5) {
            String date1 = date.substring(2, date.length());
            String str = "";
            if (date1.equals("星期五")) {
                str = "周五";
            } else if (date1.equals("星期六")) {
                str = "周六";
            } else if (date1.equals("星期日")) {
                str = "周日";
            } else if (date1.equals("星期一")) {
                str = "周一";
            } else if (date1.equals("星期二")) {
                str = "周二";
            } else if (date1.equals("星期三")) {
                str = "周三";
            } else if (date1.equals("星期四")) {
                str = "周四";
            }
            return str;

        } else {
            String date1 = date.substring(3, date.length());
            String str = "";
            if (date1.equals("星期五")) {
                str = "周五";
            } else if (date1.equals("星期六")) {
                str = "周六";
            } else if (date1.equals("星期天")) {
                str = "周日";
            } else if (date1.equals("星期一")) {
                str = "周一";
            } else if (date1.equals("星期二")) {
                str = "周二";
            } else if (date1.equals("星期三")) {
                str = "周三";
            } else if (date1.equals("星期四")) {
                str = "周四";
            }
            return str;
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

    public String getFengLi() {
        return fengLi.substring(10, fengLi.length() - 3);
    }

    public void setFengLi(String fengLi) {
        this.fengLi = fengLi;
    }

    public String getLow() {
        return low.substring(2, low.length());
    }

    public void setLow(String low) {
        this.low = low;
    }

    public String getFengXiang() {
        return fengXiang;
    }

    public void setFengXiang(String fengXiang) {
        this.fengXiang = fengXiang;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
