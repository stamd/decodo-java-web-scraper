package org.example;

import java.io.IOException;
import java.net.http.*;
import java.net.*;
import java.time.Duration;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.nio.file.Files;
import java.nio.file.Path;

import com.opencsv.CSVWriter;
import java.nio.charset.StandardCharsets;
import java.io.Writer;

public class Main {
    // Define Quote as a static inner class so this compiles in a single file.
    static class Quote {
        private String quoteText;
        private String quoteAuthor;

        public Quote(String quoteText, String quoteAuthor) {
            this.quoteText = quoteText;
            this.quoteAuthor = quoteAuthor;
        }

        public String getQuoteText() { return quoteText; }
        public String getQuoteAuthor() { return quoteAuthor; }
    }

    public static void main(String[] args) throws IOException, InterruptedException {
        // Create an HTTP client
        HttpClient client = HttpClient.newBuilder()
                .connectTimeout(Duration.ofSeconds(10))
                .build();

        // Build a `GET` request that targets the website you want to scrape
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://quotes.toscrape.com"))
                .header("User-Agent", "Java/HttpClient")
                .GET()
                .build();

        // Send the request and capture the response body as a string
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        String pageHtml = response.body();

        // Parse the fetched HTML into a Jsoup `Document`
        Document doc = Jsoup.parse(pageHtml, "https://quotes.toscrape.com");
        // Select all quote blocks with the CSS selector
        Elements quotes = doc.select(".quote");

        // Collect parsed quotes
        List<Quote> allQuotes = new ArrayList<>();

        // For each quote, read the `.text` and `.author` elements
        for (Element quote : quotes) {
            String quoteText = quote.selectFirst(".text").text();
            String quoteAuthor = quote.selectFirst(".author").text();

            allQuotes.add(new Quote(quoteText, quoteAuthor));
        }

        // Export to JSON with Gson
        Gson gson = new GsonBuilder()
                .setPrettyPrinting()
                .create();

        String json = gson.toJson(allQuotes);
        Files.writeString(Path.of("quotes.json"), json);

        System.out.println("Wrote " + allQuotes.size() + " quotes to quotes.json");

        // Export to CSV with OpenCSV
        Path out = Path.of("quotes.csv");
        try (Writer w = Files.newBufferedWriter(out, StandardCharsets.UTF_8);
             CSVWriter csv = new CSVWriter(w)) {

            // Header
            csv.writeNext(new String[] { "quoteText", "quoteAuthor" });

            // Rows
            for (Quote q : allQuotes) {
                csv.writeNext(new String[] { q.getQuoteText(), q.getQuoteAuthor() });
            }
        }

        System.out.println("Wrote " + quotes.size() + " quotes to quotes.csv");

    }
}