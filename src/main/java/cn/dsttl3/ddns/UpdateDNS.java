package cn.dsttl3.ddns;

import cn.dsttl3.ddns.util.Config;
import cn.dsttl3.ddns.util.ddns.DDNS;
import com.aliyun.fc.runtime.Context;
import com.aliyun.fc.runtime.HttpRequestHandler;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;

/**
 * 阿里云函数计算http触发器函数（默认IPv6）
 * domain   域名
 * rr   二级域名
 * id   阿里云 AccessKey ID
 * secret   阿里云 AccessKey Secret
 * ip   IP地址
 */
public class UpdateDNS implements HttpRequestHandler {
    @Override
    public void handleRequest(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Context context) throws IOException, ServletException {
        String domain = httpServletRequest.getParameter("domain");
        String rr = httpServletRequest.getParameter("rr");
        String id = httpServletRequest.getParameter("id");
        String secret = httpServletRequest.getParameter("secret");
        String ip = httpServletRequest.getParameter("ip");
        String json = DDNS.updateDNS(domain,rr, Config.domainType,Config.Region_ID,id,secret,ip);
        httpServletResponse.setStatus(200);
        httpServletResponse.setHeader("content-type", "application/json; charset=utf-8");
        OutputStream out = httpServletResponse.getOutputStream();
        out.write(json.getBytes());
        out.flush();
        out.close();
    }
}
