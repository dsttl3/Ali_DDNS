# Ali_DDNS

阿里DDNS

``` Java
    // 主域名 目前只支持阿里云账号下的域名
    public static String domainName = "l3.pub";
    // 主机记录 二级域名，如www、@、blog等
    public static String domainRR = "www";
    // 解析记录类型 ipv4填写A，ipv4填写AAAA
    public static String domainType = "AAAA";
    // 地域ID 默认就行
    public static String  Region_ID = "cn-qingdao";
    // 填写您的 阿里云 AccessKey ID
    public static String  AccessKey_ID = "******";
    // 填写您的 阿里云 AccessKey Secret
    public static String  AccessKey_Secret = "*****";
    // IPv6地址前缀，用于区分内网地址 按自己IP前缀填写
    public static String prefix = "240e";
    // 网卡标识，用于选择网卡 按自己外网网卡填写
    public static String netCard = "%en0";
```

![](img1.png)
