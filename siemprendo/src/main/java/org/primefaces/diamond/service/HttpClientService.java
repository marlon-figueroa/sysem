package org.primefaces.diamond.service;

import java.net.http.HttpClient;
import java.text.MessageFormat;

import org.primefaces.diamond.service.util.ServiceApi;

import com.google.gson.Gson;

public class HttpClientService {

	public static final String URL_BASE = "http://localhost:8081";

	public String getFormatterUrl(ServiceApi uri) {
		return MessageFormat.format("{1}{2}", URL_BASE, uri.getUri());
	}

	public HttpClient getClient() {
		return HttpClient.newHttpClient();
	}

	public String toJson(Object data) {
		Gson gson = new Gson();
		return gson.toJson(data);
	}

	public Object fromJson(String json, Class<?> clazz) {
		return new Gson().fromJson(json, clazz);
	}

}
