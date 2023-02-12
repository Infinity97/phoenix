package com.pheonix.core.utils.helper;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.HashMap;
import java.util.Map;

public class WebUtil {

	public static String postRequest(String url, byte[] byteArray, String... headers)
		throws IOException, InterruptedException, URISyntaxException {
		HttpClient httpClient = HttpClient.newHttpClient();
		HttpRequest request =
			HttpRequest.newBuilder()
				.uri(new URI(url))
				.headers(headers)
				.POST(byteArray == null? HttpRequest.BodyPublishers.ofByteArray(new byte[0]) : HttpRequest.BodyPublishers.ofByteArray(byteArray))
				.build();
		HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
		return response.body();
	}

	public static Map<String, String> getRequest(String url, String... headers)
		throws IOException, InterruptedException, URISyntaxException {
		HttpClient httpClient = HttpClient.newHttpClient();
		HttpRequest request = HttpRequest.newBuilder().uri(new URI(url)).headers(headers).GET().build();
		HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
		return new HashMap<>() {
			{
				put("status_code", String.valueOf(response.statusCode()));
				put("response", response.body());
			}
		};
	}

}
