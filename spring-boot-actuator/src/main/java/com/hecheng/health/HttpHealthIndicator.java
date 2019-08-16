package com.hecheng.health;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.URI;
import java.net.URISyntaxException;

@Component
public class HttpHealthIndicator implements HealthIndicator {

    @Value("${app.server.host}")
    private String serverHost;

    @Override
    public Health health() {
        Exception ex = null;
        try {
            URI uri = new URI(serverHost);
            try (Socket socket = new Socket()) {
                socket.connect(new InetSocketAddress(uri.getHost(), uri.getPort()), 1000);
            } catch (IOException e) {
                ex = e;
            }
        } catch (URISyntaxException e) {
            ex = e;
        }

        if (null != ex) {
            return Health.down(ex).build();
        }
        return Health.up().withDetail("server connect", "ok").build();
    }
}
