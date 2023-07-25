package cn.dsttl3.ddns.util.ip;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;
public class GetIPv6 {
    public static String load() {
        String IPv6 = "";
        try {
            Enumeration<NetworkInterface> networkInterfaces = NetworkInterface.getNetworkInterfaces();
            while (networkInterfaces.hasMoreElements()) {
                NetworkInterface networkInterface = networkInterfaces.nextElement();
                Enumeration<InetAddress> inetAddresses = networkInterface.getInetAddresses();
                while (inetAddresses.hasMoreElements()) {
                    InetAddress inetAddress = inetAddresses.nextElement();
                    if (inetAddress instanceof java.net.Inet6Address) {
                        if (inetAddress.getHostAddress().startsWith("240e") && inetAddress.getHostAddress().endsWith("%en0")) {
                            IPv6 = inetAddress.getHostAddress().substring(0, inetAddress.getHostAddress().length() - 4);
                        }
                    }
                }
            }
            return IPv6;
        } catch (SocketException e) {
            e.printStackTrace();
            return null;
        }
    }
}
