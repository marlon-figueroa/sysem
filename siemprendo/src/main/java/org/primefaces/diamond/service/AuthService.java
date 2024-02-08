package org.primefaces.diamond.service;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpRequest;
import java.net.http.HttpRequest.BodyPublishers;
import java.net.http.HttpResponse;
import java.text.MessageFormat;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;
import javax.servlet.http.HttpServletResponse;

import org.apache.hc.client5.http.fluent.Request;
import org.apache.hc.client5.http.fluent.Response;
import org.apache.hc.core5.http.ContentType;
import org.apache.hc.core5.http.HttpHeaders;
import org.apache.log4j.Logger;
import org.primefaces.diamond.domain.AuthenticationRequest;
import org.primefaces.diamond.domain.AuthenticationResponse;
import org.primefaces.diamond.domain.JwtUser;
import org.primefaces.diamond.service.util.ServiceApi;
import org.primefaces.diamond.service.util.SessionUtil;

@Named
@ApplicationScoped
public class AuthService extends HttpClientService {
	
	private static final Logger LOGGER = Logger.getLogger(AuthService.class);

	public AuthenticationResponse authenticate(AuthenticationRequest req) throws IOException, InterruptedException {
		String requestBody = this.toJson(req);

		String uriStr = this.getFormatterUrl(ServiceApi.AUTHENTICATE);
		LOGGER.info("CALL... " + uriStr);
		
		HttpRequest request = HttpRequest.newBuilder(URI.create(uriStr))
				.header("Content-Type", "application/json")
				.POST(BodyPublishers.ofString(requestBody)).build();

		HttpResponse<String> response = this.getClient().send(request, HttpResponse.BodyHandlers.ofString());
		String json = response.body();
		printJson(json);

		boolean verify = response.statusCode() == HttpServletResponse.SC_OK;
		AuthenticationResponse rsp = null;
		if (verify) {
			rsp = (AuthenticationResponse) this.fromJson(json, AuthenticationResponse.class);
		}
		return rsp;
	}

	public JwtUser getUser(String email) throws IOException {
		JwtUser rsp = null;

		String uriStr = this.getFormatterUrl(ServiceApi.SEARCH_BY_EMAIL);
		uriStr = MessageFormat.format(uriStr, email);

		LOGGER.info("CALL... " + uriStr);
		Response response = Request.get(uriStr)
				.setHeader(HttpHeaders.CONTENT_TYPE, ContentType.APPLICATION_JSON.getMimeType())
				.setHeader(HttpHeaders.AUTHORIZATION, "Bearer " + SessionUtil.getAccessToken())
				.execute();
		
		boolean verify = response.returnResponse().getCode() == HttpServletResponse.SC_OK;
		if (verify) {
			String json = response.returnContent().asString();
			printJson(json);
			rsp = (JwtUser) this.fromJson(json, JwtUser.class);
		}

		return rsp;
	}

}
