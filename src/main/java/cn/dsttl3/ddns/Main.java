package cn.dsttl3.ddns;
import cn.dsttl3.ddns.util.Config;
import cn.dsttl3.ddns.util.ddns.DDNS;
import cn.dsttl3.ddns.util.ip.GetIPv6;

public class Main {
    public static void main(String[] args) {
        System.out.println(args[0]);
        System.out.println(args[1]);
        System.out.println(args[2]);
        System.out.println(args[3]);
        String ip = GetIPv6.load(Config.prefix,Config.netCard);
        String r = DDNS.updateDNS(args[0],args[1],Config.domainType,
                Config.Region_ID,args[2],args[3],ip);
        System.out.println(r);
    }
}