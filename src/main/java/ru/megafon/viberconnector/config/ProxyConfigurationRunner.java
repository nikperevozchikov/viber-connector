package ru.megafon.viberconnector.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class ProxyConfigurationRunner implements ApplicationRunner {
    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @Override
    public void run(ApplicationArguments args) throws Exception {
        login_proxy();
    }

    private void login_proxy() {
        if (true) {
            System.setProperty("javax.net.ssl.trustStore", "msk***.jks");
            System.setProperty("javax.net.ssl.keyStore", "msk***.jks");
            System.setProperty("javax.net.ssl.keyStorePassword", "******");
            log.info("proxy passed");
        }
    }
}

