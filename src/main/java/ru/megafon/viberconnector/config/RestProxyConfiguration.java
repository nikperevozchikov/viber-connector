package ru.megafon.viberconnector.config;

import org.apache.http.HttpHost;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.ProxyAuthenticationStrategy;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import javax.annotation.PostConstruct;

@Component
public class RestProxyConfiguration {

    private RestTemplate restTemplate;

    @PostConstruct
    public void init() {
        this.restTemplate = new RestTemplate();

        final int proxyPortNum = Integer.parseInt("312*");
        final CredentialsProvider credsProvider = new BasicCredentialsProvider();
        credsProvider.setCredentials(new AuthScope("vlg-proxy.*******.ru", proxyPortNum), new UsernamePasswordCredentials("***", "***"));
        final HttpClientBuilder clientBuilder = HttpClientBuilder.create();
        clientBuilder.useSystemProperties();
        clientBuilder.setProxy(new HttpHost("vlg-proxy.*******.ru", proxyPortNum));
        clientBuilder.setDefaultCredentialsProvider(credsProvider);
        clientBuilder.setProxyAuthenticationStrategy(new ProxyAuthenticationStrategy());
        final CloseableHttpClient client = clientBuilder.build();
        final HttpComponentsClientHttpRequestFactory factory = new HttpComponentsClientHttpRequestFactory();
        factory.setHttpClient(client);
        restTemplate.setRequestFactory(factory);
    }

    public RestTemplate getRestTemplate() {
        return restTemplate;
    }
}
