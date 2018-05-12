package demo.com.watherpro.pojo;

import android.util.Log;

import java.util.List;

/**
 * Created by Shinelon on 2017/12/5.
 */

public class MyData {
    private MyYesterDay myYesterDay;
    private String city;
    private String aqi;
    private List<MyForeCast> myForeCastList;
    private String ganMao;
    private String winDu;

    public MyYesterDay getMyYesterDay() {
        return myYesterDay;
    }

    public void setMyYesterDay(MyYesterDay myYesterDay) {
        this.myYesterDay = myYesterDay;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getAqi() {
        String str = "";
        int i = Integer.parseInt(aqi.trim());
        if (i >= 0 && i <= 50) {
            str = i + " 优 >";
        } else if (i > 50 && i <= 100) {
            str = i + " 良 >";
        } else if (i > 100 && i <= 150) {
            str = i + " 轻度污染 >";
        } else if (i > 150 && i <= 200) {
            str = i + " 中度污染 >";
        } else if (i > 200 && i <= 300) {
            str = i + " 重度污染 >";
        } else {
            str = i + " 严重污染 >";
        }
        return str;
    }

    public void setAqi(String aqi) {
        this.aqi = aqi;
    }

    public List<MyForeCast> getMyForeCastList() {
        return myForeCastList;
    }

    public void setMyForeCastList(List<MyForeCast> myForeCastList) {
        this.myForeCastList = myForeCastList;
    }

    public String getGanMao() {
        return ganMao;
    }

    public void setGanMao(String ganMao) {
        this.ganMao = ganMao;
    }

    public String getWinDu() {
        return winDu;
    }

    public void setWinDu(String winDu) {
        this.winDu = winDu;
    }
}
