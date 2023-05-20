package cn.dsttl3.ddns.util.ddns.bean;

public class JsonBean {
    private String ipv4;
    private String ipv6;
    private String domainName;
    private String domainRR;
    private String domainType;
    private String recordsIP;
    private boolean updata;

    public String getIpv4() {
        return ipv4;
    }

    public void setIpv4(String ipv4) {
        this.ipv4 = ipv4;
    }

    public String getIpv6() {
        return ipv6;
    }

    public void setIpv6(String ipv6) {
        this.ipv6 = ipv6;
    }

    public String getDomainName() {
        return domainName;
    }

    public void setDomainName(String domainName) {
        this.domainName = domainName;
    }

    public String getDomainRR() {
        return domainRR;
    }

    public void setDomainRR(String domainRR) {
        this.domainRR = domainRR;
    }

    public String getDomainType() {
        return domainType;
    }

    public void setDomainType(String domainType) {
        this.domainType = domainType;
    }

    public String getRecordsIP() {
        return recordsIP;
    }

    public void setRecordsIP(String recordsIP) {
        this.recordsIP = recordsIP;
    }

    public boolean isUpdata() {
        return updata;
    }

    public void setUpdata(boolean updata) {
        this.updata = updata;
    }
}
