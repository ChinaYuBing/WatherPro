package demo.com.watherpro;

import android.content.ComponentName;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.media.Image;
import android.os.Build;
import android.os.Handler;
import android.os.Message;
import android.provider.CalendarContract;
import android.support.annotation.RequiresApi;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.SimpleFormatter;

import demo.com.watherpro.adapter.GrideAdapter;
import demo.com.watherpro.adapter.MyAdapter;
import demo.com.watherpro.pojo.GridePo;
import demo.com.watherpro.pojo.MyData;
import demo.com.watherpro.pojo.MyForeCast;
import demo.com.watherpro.pojo.MyWather;
import demo.com.watherpro.pojo.MyYesterDay;

public class MainActivity extends AppCompatActivity {

    private String strJson = "";
    private String httpUrl = "http://wthrcdn.etouch.cn/weather_mini?citykey=101010100";
    private Handler handler;
    private TextView textType, textHigh, textFengXiang, textFengLi, textAqi;
    private TextView yTextDay, yTextDate, yTextType, yTextMax, yTextMin;
    private ImageView yImageView;
    private ListView listView;
    private RelativeLayout relativeLayout;
    private GridView gridView;
    private List<GridePo> gridePoList = new ArrayList<>();
    private String strDate, strDate1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (Build.VERSION.SDK_INT >= 21) {
            View decorView = getWindow().getDecorView();
            int option = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_LAYOUT_STABLE;
            decorView.setSystemUiVisibility(option);
            getWindow().setStatusBarColor(Color.TRANSPARENT);
        }

        initView();
        getThread();
        addGrid();

        gridView.setAdapter(new GrideAdapter(MainActivity.this, gridePoList));

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (position == 0) {
                    Intent intent = new Intent();
                    intent.setComponent(new ComponentName("com.android.calendar",
                            "com.android.calendar.LaunchActivity"));
                    startActivity(intent);
                }
            }
        });

    }

    //初始化佈局
    private void initView() {
        textType = (TextView) findViewById(R.id.textType);
        textHigh = (TextView) findViewById(R.id.textHigh);
        textFengXiang = (TextView) findViewById(R.id.textFengXiang);
        textFengLi = (TextView) findViewById(R.id.textFengLi);
        textAqi = (TextView) findViewById(R.id.textAqi);
        listView = (ListView) findViewById(R.id.listView);


        yImageView = (ImageView) findViewById(R.id.ytextImage);
        yTextDay = (TextView) findViewById(R.id.ytextDay);
        yTextDate = (TextView) findViewById(R.id.ytextDate);
        yTextType = (TextView) findViewById(R.id.ytextType1);
        yTextMax = (TextView) findViewById(R.id.ymaxHigh);
        yTextMin = (TextView) findViewById(R.id.yminHigh);

        relativeLayout = (RelativeLayout) findViewById(R.id.myRelative);
        gridView = (GridView) findViewById(R.id.gradeView);

    }

    //获取JSON数据
    private void getHttpRequset() {
        InputStream inputStream = null;
        InputStreamReader inputStreamReader = null;
        BufferedReader bufferedReader = null;
        try {

            URL urlObject = new URL(httpUrl);
            HttpURLConnection urlConnection = (HttpURLConnection) urlObject.openConnection();
            urlConnection.setRequestMethod("GET");
            if (urlConnection.getResponseCode() == 200) {
                inputStream = urlConnection.getInputStream();
                inputStreamReader = new InputStreamReader(inputStream);
                bufferedReader = new BufferedReader(inputStreamReader);
                String data = null;
                while ((data = bufferedReader.readLine()) != null) {
                    strJson += data;
                }
                Message message = new Message();
                message.obj = strJson;
                handler.sendMessage(message);
            }

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //开启线程 解析JSON数据
    private void getThread() {

        new Thread() {
            @Override
            public void run() {
                super.run();
                getHttpRequset();
            }
        }.start();

        handler = new Handler() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                if (msg != null) {
                    if (msg.obj != null) {
                        strJson = msg.obj.toString();
                        Log.e("str", strJson);
                        try {
                            JSONObject jsonObject1 = new JSONObject(strJson);
                            JSONObject jsonData = jsonObject1.getJSONObject("data");

                            JSONObject yestDay = jsonData.getJSONObject("yesterday");
                            MyYesterDay myYesterDay = new MyYesterDay();
                            myYesterDay.setDate(yestDay.getString("date"));
                            myYesterDay.setHigh(yestDay.getString("high"));
                            myYesterDay.setFx(yestDay.getString("fx"));
                            myYesterDay.setLow(yestDay.getString("low"));
                            myYesterDay.setFl(yestDay.getString("fl"));
                            myYesterDay.setType(yestDay.getString("type"));

                            List<MyForeCast> myForeCastList = new ArrayList<>();
                            JSONArray foreCast = jsonData.getJSONArray("forecast");
                            for (int i = 0; i < foreCast.length(); i++) {
                                JSONObject jsonObject = foreCast.getJSONObject(i);
                                MyForeCast myForeCast = new MyForeCast();
                                myForeCast.setDate(jsonObject.getString("date"));
                                myForeCast.setHigh(jsonObject.getString("high"));
                                myForeCast.setFengLi(jsonObject.getString("fengli"));
                                myForeCast.setLow(jsonObject.getString("low"));
                                myForeCast.setFengXiang(jsonObject.getString("fengxiang"));
                                myForeCast.setType(jsonObject.getString("type"));
                                myForeCastList.add(myForeCast);
                            }

                            MyData myData = new MyData();
                            myData.setCity(jsonData.getString("city"));
                            myData.setAqi(jsonData.getString("aqi"));
                            myData.setGanMao(jsonData.getString("ganmao"));
                            myData.setMyForeCastList(myForeCastList);
                            myData.setMyYesterDay(myYesterDay);
                            myData.setWinDu(jsonData.getString("wendu"));

                            MyWather myWather = new MyWather();
                            myWather.setDesc(jsonObject1.getString("desc"));
                            myWather.setStatus(jsonObject1.getInt("status"));
                            myWather.setMyData(myData);

                            if (myWather != null) {
                                List<MyForeCast> foreCasts = myWather.getMyData().getMyForeCastList();
                                if (foreCasts != null) {
                                    if (foreCast != null) {
                                        textType.setText(foreCasts.get(0).getType());
                                        textFengLi.setText(foreCasts.get(0).getFengLi());
                                        textFengXiang.setText(foreCasts.get(0).getFengXiang());
                                        textHigh.setText(myData.getWinDu() + "°");
                                        textAqi.setText(myData.getAqi());

                                        if (foreCasts.get(0).getType().equals("多云")) {
                                            relativeLayout.setBackground(getDrawable(R.mipmap.cloudy));
                                        } else if (foreCasts.get(0).getType().equals("晴天")) {
                                            relativeLayout.setBackground(getDrawable(R.mipmap.suny_day));
                                        }

                                        yImageView.setImageResource(R.mipmap.sun_ic2);
                                        yTextDay.setText("昨天");
                                        yTextDate.setText(myYesterDay.getDate1());
                                        yTextType.setText(myYesterDay.getType());
                                        yTextMax.setText(myYesterDay.getHigh());
                                        yTextMin.setText(myYesterDay.getLow());

                                        List<MyForeCast> myForeCastList1 = new ArrayList<>();
                                        for (int i = 0; i < foreCasts.size(); i++) {
                                            myForeCastList1.add(foreCasts.get(i));
                                        }
                                        listView.setAdapter(new MyAdapter(MainActivity.this, myForeCastList1, myYesterDay));

                                    }
                                }
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        };

    }

    private void addGrid() {
        GridePo gridePo1 = new GridePo();
        gridePo1.setImage(R.mipmap.riqi);
        SimpleDateFormat formatter = new SimpleDateFormat("MM月dd日");
        SimpleDateFormat format = new SimpleDateFormat("EEEE");
        Date date = new Date(System.currentTimeMillis());
        strDate = formatter.format(date);
        strDate1 = format.format(date);
        gridePo1.setName(strDate);
        gridePo1.setContent(strDate1);
        gridePoList.add(gridePo1);

        GridePo gridePo2 = new GridePo();
        gridePo2.setImage(R.mipmap.chuanyi);
        gridePo2.setName("穿衣指数");
        gridePo2.setContent("凉");
        gridePoList.add(gridePo2);

        GridePo gridePo3 = new GridePo();
        gridePo3.setImage(R.mipmap.ziwaixian);
        gridePo3.setName("紫外线指数");
        gridePo3.setContent("最弱");
        gridePoList.add(gridePo3);

        GridePo gridePo4 = new GridePo();
        gridePo4.setImage(R.mipmap.yundong);
        gridePo4.setName("运动指数");
        gridePo4.setContent("不适宜");
        gridePoList.add(gridePo4);

        GridePo gridePo5 = new GridePo();
        gridePo5.setImage(R.mipmap.fanmao);
        gridePo5.setName("感冒指数");
        gridePo5.setContent("易发");
        gridePoList.add(gridePo5);

        GridePo gridePo6 = new GridePo();
        gridePo6.setImage(R.mipmap.xiche);
        gridePo6.setName("洗车指数");
        gridePo6.setContent("适宜");
        gridePoList.add(gridePo6);

        GridePo gridePo7 = new GridePo();
        gridePo7.setImage(R.mipmap.diaoyu);
        gridePo7.setName("钓鱼指数");
        gridePo7.setContent("较适宜");
        gridePoList.add(gridePo7);

        GridePo gridePo8 = new GridePo();
        gridePo8.setImage(R.mipmap.shenghuo);
        gridePo8.setName("生活指数");
        gridePo8.setContent("精品购");
        gridePoList.add(gridePo8);

    }


}
