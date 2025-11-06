package org.example;

import java.net.http.*;
import java.net.*;
import java.util.*;
import java.util.concurrent.*;

public class MainParallelScraper {
    public static void main(String[] args) throws Exception {
        List<String> urls = List.of(
                "https://quotes.toscrape.com/page/1",
                "https://quotes.toscrape.com/page/2",
                "https://quotes.toscrape.com/page/3"
        );

        HttpClient client = HttpClient.newHttpClient();
        ExecutorService executor = Executors.newFixedThreadPool(5);

        for (String url : urls) {
            executor.submit(() -> {
                try {
                    HttpRequest request = HttpRequest.newBuilder()
                            .uri(URI.create(url))
                            .header("User-Agent", "JavaScraper/1.0")
                            .GET()
                            .build();

                    HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
                    System.out.println(url + " - " + response.statusCode());
                } catch (Exception e) {
                    System.err.println("Error fetching " + url + ": " + e.getMessage());
                }
            });
        }

        executor.shutdown();
        executor.awaitTermination(1, TimeUnit.MINUTES);
    }
}
