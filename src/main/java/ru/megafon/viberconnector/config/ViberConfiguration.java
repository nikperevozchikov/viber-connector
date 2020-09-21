package ru.megafon.viberconnector.config;

import com.viber.bot.ViberSignatureValidator;
import org.apache.http.client.HttpClient;
import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.ssl.SSLContexts;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;

import javax.annotation.Nullable;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import java.io.File;
import java.util.HashSet;

@Configuration
public class ViberConfiguration {

    @Value("${application.viber-bot.auth-token}")
    private String authToken;

    @Value("${application.viber-bot.webhook-url}")
    private String webhookUrl;

    @Value("${application.viber-bot.name}")
    private String name;

    @Nullable
    @Value("${application.viber-bot.avatar:@null}")
    private String avatar;

    @Value("${http.client.ssl.trust-store}")
    private String trustStore;

    @Value("${http.client.ssl.trust-store-password}")
    private char[] trustStorePassword;

    @Value("${http.client.maxPoolSize}")
    private Integer maxPoolSize;

    @Bean
    ViberSignatureValidator signatureValidator() {
        return new ViberSignatureValidator(authToken);
    }


    @Bean
    public ClientHttpRequestFactory httpRequestFactory() {
        return new HttpComponentsClientHttpRequestFactory(httpClient());
    }

    @Bean
    public HashSet tokenCache() {
        return new HashSet<Long>();
    }

    @Bean
    public HttpClient httpClient() {

        // Trust own CA and all child certs
        Registry<ConnectionSocketFactory> socketFactoryRegistry = null;
        File file = new File("/data/build_java_app/test/" + trustStore);
        try {
            SSLContext sslContext = SSLContexts
                    .custom()
                    .loadTrustMaterial(file,
                            trustStorePassword)
                    .build();

            // Since only our own certs are trusted, hostname verification is probably safe to bypass
            SSLConnectionSocketFactory sslSocketFactory = new SSLConnectionSocketFactory(sslContext,
                    new HostnameVerifier() {

                        @Override
                        public boolean verify(String s, SSLSession sslSession) {
                            return true;
                        }

                    });

            socketFactoryRegistry = RegistryBuilder.<ConnectionSocketFactory>create()
                    .register("http", PlainConnectionSocketFactory.getSocketFactory())
                    .register("https", sslSocketFactory)
                    .build();

        } catch (Exception e) {
            e.printStackTrace();
        }

        PoolingHttpClientConnectionManager connectionManager = new PoolingHttpClientConnectionManager(socketFactoryRegistry);
        connectionManager.setMaxTotal(maxPoolSize);
        // This client is for internal connections so only one route is expected
        connectionManager.setDefaultMaxPerRoute(maxPoolSize);
        return HttpClientBuilder.create()
                .setConnectionManager(connectionManager)
                .disableCookieManagement()
                .disableAuthCaching()
                .build();
    }
}

