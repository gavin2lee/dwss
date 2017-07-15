package com.gl.order.client.sender;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.StatusLine;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import com.gl.order.client.config.HttpServerConfiguration;
import com.gl.order.client.exception.ClientException;

public class HttpRemoteServer {
    private HttpServerConfiguration httpServerConfiguration;

    public HttpRemoteServer() {
        super();
        httpServerConfiguration = new HttpServerConfiguration();
    }

    public HttpRemoteServer(HttpServerConfiguration httpServerConfiguration) {
        super();
        this.httpServerConfiguration = httpServerConfiguration;
    }

    public String post(String body, String context) throws ClientException {
        CloseableHttpClient client = HttpClients.createDefault();

        HttpHost httpHost = new HttpHost(httpServerConfiguration.getRemoteHost(),
                Integer.parseInt(httpServerConfiguration.getRemotePort()), httpServerConfiguration.getSchema());
        HttpPost httpPost = new HttpPost(tidyContextPath(context));

        try {
            HttpEntity httpEntity = new StringEntity(body, ContentType.APPLICATION_JSON);
            httpPost.setEntity(httpEntity);
            HttpResponse resp = client.execute(httpHost, httpPost);

            StatusLine statusLine = resp.getStatusLine();
            if (HttpStatus.SC_OK != statusLine.getStatusCode()) {
                throw new ClientException(statusLine.getReasonPhrase());
            }

            HttpEntity respEntity = resp.getEntity();
            InputStream content = respEntity.getContent();

            byte[] buf = new byte[1024];
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            int len = 0;
            while ((len = content.read(buf)) != -1) {
                bos.write(buf, 0, len);
            }

            content.close();

            return bos.toString();
        } catch (ClientProtocolException e) {
            throw new ClientException(e);
        } catch (IOException e) {
            throw new ClientException(e);
        } finally {
            try {
                client.close();
            } catch (IOException e) {
                throw new ClientException(e);
            }
        }
    }

    public void setHttpServerConfiguration(HttpServerConfiguration httpServerConfiguration) {
        this.httpServerConfiguration = httpServerConfiguration;
    }

    private String tidyContextPath(String context) {
        return context;
    }
}
