package security;

import java.io.InputStream;
import java.security.Policy;

public class NetworkTest {
    public static void main(String[] args) {

        try {
            // Attempt to make a network connection (this should fail)
            java.net.Socket socket = new java.net.Socket("www.google.com", 80);
            InputStream inputStream = socket.getInputStream();
        } catch (SecurityException se) {
            System.out.println("Network access denied by Security Manager!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

