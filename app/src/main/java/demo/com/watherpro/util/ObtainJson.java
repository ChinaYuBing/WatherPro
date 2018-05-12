package demo.com.watherpro.util;

import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by Shinelon on 2017/12/6.
 */

public class ObtainJson {
    private static String httpUrl = "http://wthrcdn.etouch.cn/weather_mini?citykey=101010100";

    public static String getJson() {
        try {
            URL urlObject = new URL(httpUrl);
            HttpURLConnection urlConnection = (HttpURLConnection) urlObject.openConnection();
            urlConnection.setRequestMethod("GET");
            if (urlConnection.getResponseCode() == 200) {
                InputStream inputStream = urlConnection.getInputStream();
                InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                String data = null;
                String strJson = "";
                while ((data = bufferedReader.readLine()) != null) {
                    strJson += data;
                }
                Log.e("strJson==", strJson);
                return strJson;
            }else{

            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

}
