package cn.dsttl3.ddns.util.ddns;

import cn.dsttl3.ddns.util.Config;
import cn.dsttl3.ddns.util.ddns.bean.JsonBean;
import cn.dsttl3.ddns.util.ip.GetIP;
import cn.dsttl3.ddns.util.ip.GetIPv6;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.alidns.model.v20150109.DescribeDomainRecordsRequest;
import com.aliyuncs.alidns.model.v20150109.DescribeDomainRecordsResponse;
import com.aliyuncs.alidns.model.v20150109.UpdateDomainRecordRequest;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.profile.DefaultProfile;
import com.google.gson.Gson;

import java.util.List;

public class DDNS {
    /**
     * 获取主域名的所有解析记录列表
     */
    private DescribeDomainRecordsResponse describeDomainRecords(DescribeDomainRecordsRequest request, IAcsClient client) {
        try {
            return client.getAcsResponse(request);
        } catch (ClientException e) {
            e.printStackTrace();
            throw new RuntimeException();
        }
    }

    /**
     * 修改解析记录
     */
    private void updateDomainRecord(UpdateDomainRecordRequest request, IAcsClient client) {
        try {
            client.getAcsResponse(request);
        } catch (ClientException e) {
            e.printStackTrace();
            throw new RuntimeException();
        }
    }

    public static String updateDNS(String domainName, String domainRR, String domainType, String Region_ID,
                                   String AccessKey_ID, String AccessKey_Secret, String currentHostIP) {
        JsonBean jsonBean = new JsonBean();

        // 初始化阿里云客户端配置，用于后续调用API
        DefaultProfile profile = DefaultProfile.getProfile(Region_ID, AccessKey_ID, AccessKey_Secret);
        IAcsClient client = new DefaultAcsClient(profile);

        // 创建DDNS服务实例
        DDNS ddns = new DDNS();

        // 创建请求对象，用于查询域名记录
        DescribeDomainRecordsRequest describeDomainRecordsRequest = new DescribeDomainRecordsRequest();
        // 设置查询的域名
        describeDomainRecordsRequest.setDomainName(domainName);
        // 设置查询的主机记录
        describeDomainRecordsRequest.setRRKeyWord(domainRR);
        // 设置查询的记录类型
        describeDomainRecordsRequest.setType(domainType);

        // 调用DDNS服务的查询方法，获取域名记录列表
        DescribeDomainRecordsResponse describeDomainRecordsResponse = ddns.describeDomainRecords(describeDomainRecordsRequest, client);
        // 获取查询结果中的域名记录列表
        List<DescribeDomainRecordsResponse.Record> domainRecords = describeDomainRecordsResponse.getDomainRecords();


        // 最新的一条解析记录
        if (!domainRecords.isEmpty()) {
            DescribeDomainRecordsResponse.Record record = domainRecords.get(0);
            String recordId = record.getRecordId();
            System.out.println("解析记录ID：" + recordId);
            String recordsValue = record.getValue();
            jsonBean.setRecordsIP(recordsValue);
            System.out.println("当前DNS服务器IP为：\t" + recordsValue + "。");
            // 当前主机公网IP
            if (domainType.equals("A")) {
                jsonBean.setIpv4(currentHostIP);
            } else if (domainType.equals("AAAA")) {
                jsonBean.setIpv6(currentHostIP);
            }
            System.out.println("当前主机公网 IP为：\t" + currentHostIP + "。");
            if (!currentHostIP.equals(recordsValue)) {
                UpdateDomainRecordRequest updateDomainRecordRequest = new UpdateDomainRecordRequest();
                updateDomainRecordRequest.setRR(domainRR);
                updateDomainRecordRequest.setRecordId(recordId);
                updateDomainRecordRequest.setValue(currentHostIP);
                updateDomainRecordRequest.setType(domainType);
                ddns.updateDomainRecord(updateDomainRecordRequest, client);
                jsonBean.setUpdata(true);
            }
            jsonBean.setDomainRR(domainRR);
            jsonBean.setDomainName(domainName);
            jsonBean.setDomainType(domainType);
            String json = new Gson().toJson(jsonBean);
            System.out.println("[" + domainRR + "." + domainName + "]设置完成。");
            return json;
        } else {
            System.out.println("没有找到[" + domainRR + "." + domainName + "]解析记录,请前往[https://dns.console.aliyun.com/]手动添加后再试。");
            return "{\"code\": 400,\"msg\": \"没有找到[" + domainRR + "." + domainName + "]解析记录,请前往[https://dns.console.aliyun.com/]手动添加后再试。\"}";
        }
    }
}
