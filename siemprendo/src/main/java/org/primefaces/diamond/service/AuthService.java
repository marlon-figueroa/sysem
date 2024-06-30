package org.primefaces.diamond.service;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpRequest;
import java.net.http.HttpRequest.BodyPublishers;
import java.text.MessageFormat;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;
import javax.servlet.http.HttpServletResponse;

import org.apache.hc.client5.http.async.methods.SimpleHttpRequest;
import org.apache.hc.client5.http.async.methods.SimpleHttpResponse;
import org.apache.hc.client5.http.async.methods.SimpleRequestBuilder;
import org.apache.hc.client5.http.async.methods.SimpleRequestProducer;
import org.apache.hc.client5.http.async.methods.SimpleResponseConsumer;
import org.apache.hc.client5.http.impl.async.CloseableHttpAsyncClient;
import org.apache.hc.client5.http.impl.async.HttpAsyncClients;
import org.apache.hc.core5.concurrent.FutureCallback;
import org.apache.hc.core5.http.ContentType;
import org.apache.hc.core5.http.HttpHeaders;
import org.apache.hc.core5.http.HttpHost;
import org.apache.hc.core5.http.HttpStatus;
import org.apache.hc.core5.http.ParseException;
import org.apache.hc.core5.http.message.StatusLine;
import org.apache.hc.core5.io.CloseMode;
import org.apache.hc.core5.reactor.IOReactorConfig;
import org.apache.hc.core5.util.Timeout;
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

        java.net.http.HttpResponse<String> response = this.getClient().send(request, java.net.http.HttpResponse.BodyHandlers.ofString());
        String json = response.body();
        printJson(json);

        boolean verify = response.statusCode() == HttpServletResponse.SC_OK;
        AuthenticationResponse rsp = null;
        if (verify) {
            rsp = (AuthenticationResponse) this.fromJson(json, AuthenticationResponse.class);
        }
        return rsp;
    }

    public JwtUser getUser(String email) throws IOException, ParseException, InterruptedException, ExecutionException {

        JwtUser rsp = null;
        String requestUri = MessageFormat.format(ServiceApi.SEARCH_BY_EMAIL.getUri(), email);

        final IOReactorConfig ioReactorConfig = IOReactorConfig.custom().setSoTimeout(Timeout.ofSeconds(5)).build();
        final CloseableHttpAsyncClient client = HttpAsyncClients.custom().setIOReactorConfig(ioReactorConfig).build();
        client.start();

        final HttpHost target = new HttpHost(HOST_CLIENT, PORT_CLIENT);

        final SimpleHttpRequest request = SimpleRequestBuilder.get()
                .setHttpHost(target)
                .addHeader(HttpHeaders.CONTENT_TYPE, ContentType.APPLICATION_JSON.getMimeType())
                .addHeader(HttpHeaders.AUTHORIZATION, "Bearer " + SessionUtil.getAccessToken())
                .setPath(requestUri)
                .build();

        final Future<SimpleHttpResponse> future = client.execute(SimpleRequestProducer.create(request),
                SimpleResponseConsumer.create(), new FutureCallback<SimpleHttpResponse>() {

            @Override
            public void completed(final SimpleHttpResponse response) {
                LOGGER.info(request + " -> " + new StatusLine(response));
                LOGGER.info(response.getBody());
            }

            @Override
            public void failed(final Exception ex) {
                LOGGER.info(request + "->" + ex);
            }

            @Override
            public void cancelled() {
                LOGGER.info(request + " cancelled");
            }

        });

        SimpleHttpResponse response = future.get();
        if (response.getCode() == HttpStatus.SC_OK) {
            String json = response.getBodyText();
            printJson(json);
            rsp = (JwtUser) fromJson(json, JwtUser.class);
        }

        LOGGER.info("Shutting down...OK");
        client.close(CloseMode.GRACEFUL);

        return rsp;
    }

}
