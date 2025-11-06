package org.example;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

public class MainPagination {
    public static void main(String[] args) throws Exception {
        int page = 1;
        boolean hasNext = true;

        while (hasNext) {
            String url = "https://quotes.toscrape.com/page/" + page;
            Document doc = Jsoup.connect(url)
                    .userAgent("Mozilla/5.0 (compatible; JavaScraper/1.0)")
                    .timeout(10000)
                    .get();

            Elements quotes = doc.select(".quote");
            if (quotes.isEmpty()) break; // Stop when no more products

            System.out.println("Page " + page + ": " + quotes.size() + " items");

            // Extract and store data here
            page++;

            // Small delay helps avoid hitting rate limits
            Thread.sleep(1500);
        }
    }
}

