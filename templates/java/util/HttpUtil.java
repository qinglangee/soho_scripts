package util;

import java.io.IOException;
import java.net.ProxySelector;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;

import service.DataProvider;

public class HttpUtil {

    private HttpUtil(){}

    public static String get(String url){

        HttpRequest request = null;
        try {
            request = HttpRequest.newBuilder()
                .uri(new URI(url))
                .header("X-Finnhub-Token", DataProvider.getInstance().getApiKey())
                .GET()
                .build();
        } catch (URISyntaxException e1) {
            e1.printStackTrace();
        }

        HttpResponse<String> response = null;
        try {
            response = HttpClient
                .newBuilder()
                .proxy(ProxySelector.getDefault())
                .build()
                .send(request, BodyHandlers.ofString());
            return response.body();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return null;

    }
}
