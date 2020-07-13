package me.decentos.actuator;

import org.springframework.boot.actuate.info.Info;
import org.springframework.boot.actuate.info.InfoContributor;
import org.springframework.stereotype.Component;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.Map;

@Component
public class NetworkDataContributor implements InfoContributor {

    @Override
    public void contribute(Info.Builder builder) {
        Map<String, Object> details = new HashMap<>();
        try {
            details.put("Server-IP-Address", InetAddress.getLocalHost().getHostAddress());
            details.put("Server-OS", System.getProperty("os.name").toLowerCase());

        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
        builder.withDetail("network-data", details);
    }
}
