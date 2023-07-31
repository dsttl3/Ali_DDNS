package cn.dsttl3.ddns;
import cn.dsttl3.ddns.util.Config;
import cn.dsttl3.ddns.util.ddns.DDNS;
public class Main {
    public static void main(String[] args) {
        String r = DDNS.updateDNS(Config.domainName,Config.domainRR,Config.domainType,
                Config.Region_ID,Config.AccessKey_ID,Config.AccessKey_Secret);
        System.out.println(r);
    }
}