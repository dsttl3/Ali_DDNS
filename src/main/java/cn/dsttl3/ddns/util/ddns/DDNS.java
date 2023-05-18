package cn.dsttl3.ddns.util.ddns;

import cn.dsttl3.ddns.util.ip.GetIP;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.alidns.model.v20150109.DescribeDomainRecordsRequest;
import com.aliyuncs.alidns.model.v20150109.DescribeDomainRecordsResponse;
import com.aliyuncs.alidns.model.v20150109.UpdateDomainRecordRequest;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.profile.DefaultProfile;
import java.util.List;

public class DDNS {
    /**
     * 获取主域名的所有解析记录列表
     */
    private DescribeDomainRecordsResponse describeDomainRecords(DescribeDomainRecordsRequest request,IAcsClient client) {
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
    public static String updateDNS(String domainName, String domainRR, String domainType, String Region_ID, String AccessKey_ID, String AccessKey_Secret) {
        // 设置鉴权参数，初始化客户端
        DefaultProfile profile = DefaultProfile.getProfile(Region_ID, AccessKey_ID, AccessKey_Secret);
        IAcsClient client = new DefaultAcsClient(profile);
        DDNS ddns = new DDNS();
        // 查询指定二级域名的最新解析记录
        DescribeDomainRecordsRequest describeDomainRecordsRequest = new DescribeDomainRecordsRequest();
        describeDomainRecordsRequest.setDomainName(domainName);
        describeDomainRecordsRequest.setRRKeyWord(domainRR);
        describeDomainRecordsRequest.setType(domainType);
        DescribeDomainRecordsResponse describeDomainRecordsResponse = ddns.describeDomainRecords(describeDomainRecordsRequest, client);
        List<DescribeDomainRecordsResponse.Record> domainRecords = describeDomainRecordsResponse.getDomainRecords();
        // 最新的一条解析记录
        if (domainRecords.size() != 0) {
            DescribeDomainRecordsResponse.Record record = domainRecords.get(0);
            String recordId = record.getRecordId();
            System.out.println("解析记录ID："+recordId);
            String recordsValue = record.getValue();
            System.out.println("当前DNS服务器IP为：\t" + recordsValue + "");
            String currentHostIP = "";
            // 当前主机公网IP
            if(domainType.equals("A")){
                currentHostIP = GetIP.GetIPv4();
                if (currentHostIP == null) {
                    System.out.println("获取公网IPv4地址错误。");
                    return "获取公网IPv4地址错误。";
                }
            }else if (domainType.equals("AAAA")){
                currentHostIP = GetIP.GetIPv6();
                if (currentHostIP == null) {
                    System.out.println("获取公网IPv6地址错误。");
                    return "获取公网IPv6地址错误。";
                }
            }
            System.out.println("当前主机公网 IP为：\t" + currentHostIP + "");
            if (!currentHostIP.equals(recordsValue)) {
                UpdateDomainRecordRequest updateDomainRecordRequest = new UpdateDomainRecordRequest();
                updateDomainRecordRequest.setRR(domainRR);
                updateDomainRecordRequest.setRecordId(recordId);
                updateDomainRecordRequest.setValue(currentHostIP);
                updateDomainRecordRequest.setType(domainType);
                ddns.updateDomainRecord(updateDomainRecordRequest, client);
            }
            System.out.println("[" + domainRR + "." + domainName + "]设置完成。");
            return "[" + domainRR + "." + domainName + "]设置完成。";
        } else {
            System.out.println("没有找到[" + domainRR + "." + domainName + "]解析记录,请前往[https://dns.console.aliyun.com/]手动添加后再试。");
            return "没有找到[" + domainRR + "." + domainName + "]解析记录,请前往[https://dns.console.aliyun.com/]手动添加后再试。";
        }
    }
}
