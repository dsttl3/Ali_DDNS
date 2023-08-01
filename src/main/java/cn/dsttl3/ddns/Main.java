package cn.dsttl3.ddns;
import cn.dsttl3.ddns.util.Config;
import cn.dsttl3.ddns.util.ddns.DDNS;
public class Main {
    public static void main(String[] args) {
        System.out.println(args[0]);
        System.out.println(args[1]);
        System.out.println(args[2]);
        System.out.println(args[3]);
        String r = DDNS.updateDNS(args[0],args[1],Config.domainType,
                Config.Region_ID,args[2],args[3],Config.prefix,Config.netCard);
        System.out.println(r);
    }
}