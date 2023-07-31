package cn.dsttl3.ddns.util.ip;

import cn.dsttl3.ddns.util.ip.bean.IPBean;
import com.google.gson.Gson;
import okhttp3.Call;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import java.io.IOException;
import java.util.Objects;

/**
 * 获取外网地址
 */
public class GetIP {
    /**
     * @return 获取外网IPv4地址
     */
    public static String GetIPv4(){
        String url = "https://v4.ip.zxinc.org/info.php?type=json";
        return getIP(url);
    }
    /**
     * @return 获取外网IPv6地址
     */
    public static String GetIPv6(){
        String url = "https://v6.ip.zxinc.org/info.php?type=json";
        return getIP(url);
    }
    public static String getIP(String url){
        try {
            OkHttpClient client = new OkHttpClient();
            Request request = new Request.Builder().url(url).build();
            Call call = client.newCall(request);
            Response re = call.execute();
            String json = Objects.requireNonNull(re.body()).string();
            return new Gson().fromJson(json, IPBean.class).getData().getMyip();
        } catch (IOException e) {
            return null;
        }
    }
}
