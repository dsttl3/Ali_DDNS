package cn.dsttl3.ddns.util.ip;



import java.net.InetAddress;
        import java.net.NetworkInterface;
        import java.net.SocketException;
        import java.util.Enumeration;

public class GetIPv6 {
    public static void main(String[] args) {
        try {
            Enumeration<NetworkInterface> networkInterfaces = NetworkInterface.getNetworkInterfaces();
            while (networkInterfaces.hasMoreElements()) {
                NetworkInterface networkInterface = networkInterfaces.nextElement();
                Enumeration<InetAddress> inetAddresses = networkInterface.getInetAddresses();
                while (inetAddresses.hasMoreElements()) {
                    InetAddress inetAddress = inetAddresses.nextElement();
                    if (inetAddress instanceof java.net.Inet6Address) {
                        if(inetAddress.getHostAddress().startsWith("240e") && inetAddress.getHostAddress().endsWith("%en0")){
                            System.out.println("IPv6 address: " + inetAddress.getHostAddress());
                        }
                    }
                    if (inetAddress instanceof java.net.Inet4Address) {
//                        if(inetAddress.getHostAddress().startsWith("240e") && inetAddress.getHostAddress().endsWith("%en0")){
                            System.out.println("IPv4 address: " + inetAddress.getHostAddress());
//                        }
                    }
                }
            }
        } catch (SocketException e) {
            e.printStackTrace();
        }
    }
}
