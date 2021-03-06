package br.com.interfile.limparInstancias.utils;

import java.security.cert.X509Certificate;

import javax.net.ssl.SSLContext;

import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.TrustStrategy;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

public class IgnoreCertificateUtil {

	public static RestTemplate getRestTemplate() {
		TrustStrategy acceptingTrustStrategy = (X509Certificate[] chain, String authType) -> true;
		HttpComponentsClientHttpRequestFactory requestFactory = null;
		try {
			SSLContext sslContext = org.apache.http.ssl.SSLContexts.custom()
					.loadTrustMaterial(null, acceptingTrustStrategy)
					.build();

			SSLConnectionSocketFactory csf = new SSLConnectionSocketFactory(sslContext);

			CloseableHttpClient httpClient = HttpClients.custom()
					.setSSLSocketFactory(csf)
					.build();

			requestFactory = new HttpComponentsClientHttpRequestFactory();

			requestFactory.setHttpClient(httpClient);
		} catch (Exception e) {
			e.printStackTrace();
		}
		RestTemplate restTemplate = new RestTemplate(requestFactory);
		return restTemplate;
	}

}
