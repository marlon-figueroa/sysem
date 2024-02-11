package org.primefaces.diamond.service;

import java.net.http.HttpClient;
import java.text.MessageFormat;

import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.log4j.Logger;
import org.primefaces.diamond.service.util.ServiceApi;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

public class HttpClientService {

	private static final Logger LOGGER = Logger.getLogger(HttpClientService.class);
	public static final String URL_BASE = "http://localhost:8081";
	public static final String HOST_CLIENT = "localhost";
	public static final int PORT_CLIENT = 8081;

	public String getFormatterUrl(ServiceApi uri) {
		return MessageFormat.format("{0}{1}", URL_BASE, uri.getUri());
	}

	public HttpClient getClient() {
		return HttpClient.newHttpClient();
	}
	
	public CloseableHttpClient getHttpclient() {
		return HttpClients.createDefault(); 
	}

	public String toJson(Object data) {
		Gson gson = new Gson();
		return gson.toJson(data);
	}

	public Object fromJson(String json, Class<?> clazz) {
		return new Gson().fromJson(json, clazz);
	}

	public void printJson(String uglyJson) {
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		JsonElement jsonElement = JsonParser.parseString(uglyJson);
		String prettyJson = gson.toJson(jsonElement);
		LOGGER.info(prettyJson);
	}

}
