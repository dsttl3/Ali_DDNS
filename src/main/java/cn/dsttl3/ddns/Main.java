package cn.dsttl3.ddns;

import cn.dsttl3.ddns.util.ddns.DDNS;

public class Main {
    public static void main(String[] args) {
        // 主域名
        String domainName = "l3.pub";
        // 主机记录
        String domainRR = "www";
        // 解析记录类型
        String domainType = "AAAA";
        // 地域ID
        String  Region_ID = "cn-qingdao";
        // 您的AccessKey ID
        String  AccessKey_ID = "LTAI5tS5jHh8frJhTLMWtBmt";
        // 您的AccessKey Secret
        String  AccessKey_Secret = "UvgzM48w5DKPXKshEYW3YyV1rxO0B7";

        String r = DDNS.updateDNS(domainName,domainRR,domainType,Region_ID,AccessKey_ID,AccessKey_Secret);
        System.out.println(r);
    }
}