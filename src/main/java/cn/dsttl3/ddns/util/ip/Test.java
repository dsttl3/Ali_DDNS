package cn.dsttl3.ddns.util.ip;

import cn.dsttl3.ddns.util.Config;

public class Test {
    public static void main(String[] args) {
        System.out.println(GetIPv6.load(Config.prefix,Config.netCard));
    }
}
