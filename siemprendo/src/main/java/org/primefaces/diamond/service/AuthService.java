package org.primefaces.diamond.service;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpRequest.BodyPublishers;
import java.net.http.HttpResponse;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;
import javax.servlet.http.HttpServletResponse;

import org.primefaces.diamond.domain.AuthenticationRequest;
import org.primefaces.diamond.domain.AuthenticationResponse;
import org.primefaces.diamond.domain.JwtUser;
import org.primefaces.diamond.service.util.ServiceApi;

@Named
@ApplicationScoped
public class AuthService extends HttpClientService {

	public AuthenticationResponse authenticate(AuthenticationRequest req) throws IOException, InterruptedException {
		String requestBody = this.toJson(req);

		String uriStr = this.getFormatterUrl(ServiceApi.AUTHENTICATE);
		HttpRequest request = HttpRequest.newBuilder(URI.create(uriStr))
				.header("Content-Type", "application/json")
				.POST(BodyPublishers.ofString(requestBody))
				.build();

		HttpClient client = HttpClient.newHttpClient();
		HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

		String json = response.body();
		System.out.println(json);
		
		boolean verify = response.statusCode() == HttpServletResponse.SC_OK;

		AuthenticationResponse rsp = null;
		
		if(verify) {
			rsp = (AuthenticationResponse) this.fromJson(json, AuthenticationResponse.class);
		}
		
		return rsp;
	}
	
	public JwtUser getUser(String email) {
		JwtUser rsp = null;
		
		
		return rsp;
	}

}
