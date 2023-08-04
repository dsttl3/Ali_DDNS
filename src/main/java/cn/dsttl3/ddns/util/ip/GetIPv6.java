package cn.dsttl3.ddns.util.ip;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;
public class GetIPv6 {
    /**
     *
     * @param prefix IPv6地址前缀，用于区分内网地址
     * @param netCard 网卡标识，用于选择网卡
     * @return IPv6地址
     */
    public static String load(String prefix,String netCard) {
        String IPv6 = "";
        try {
            Enumeration<NetworkInterface> networkInterfaces = NetworkInterface.getNetworkInterfaces();
            while (networkInterfaces.hasMoreElements()) {
                NetworkInterface networkInterface = networkInterfaces.nextElement();
                Enumeration<InetAddress> inetAddresses = networkInterface.getInetAddresses();
                while (inetAddresses.hasMoreElements()) {
                    InetAddress inetAddress = inetAddresses.nextElement();
                    if (inetAddress instanceof java.net.Inet6Address) {
                        if (inetAddress.getHostAddress().startsWith(prefix) && inetAddress.getHostAddress().endsWith(netCard)) {
                            IPv6 = inetAddress.getHostAddress().substring(0, inetAddress.getHostAddress().length() - netCard.length());
                        }
                    }
                }
            }
            return IPv6;
        } catch (SocketException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }


}
