import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashSet;
import java.util.Set;

public class MultiplePages {
    private static final String BASE_URL = "https://nguoikesu.com/";
    private Set<String> visitedPages = new HashSet<>();

    public static void main(String[] args) {
        MultiplePages scraper = new MultiplePages();
        scraper.scrape(BASE_URL);
    }

    private void scrape(String url) {
        if (visitedPages.contains(url)) {
            return;
        }

        visitedPages.add(url);

        try {
            JSONObject data = scrapePage(url);
            URI uri = new URI(url);
            String[] segments = uri.getPath().split("/");
            String filename = segments.length > 0 ? segments[segments.length - 1].replaceAll("[^a-zA-Z0-9.-]", "_") + ".json" : "index.json";
            File outputDirectory = new File("out");
            if (!outputDirectory.exists()) {
                outputDirectory.mkdir();
            }
            FileWriter file = new FileWriter(new File(outputDirectory, filename));
            file.write(data.toString(4));
            file.flush();
            file.close();

            Document document = Jsoup.connect(url).get();
            Elements anchors = document.select("a[href]");
            for (Element anchor : anchors) {
                String nextPageUrl = anchor.attr("abs:href"); // Use the absolute URL directly
                try {
                    URI nextUri = new URI(nextPageUrl);
                    if (nextUri.getFragment() != null) {
                        // Ignore URLs with fragment identifiers
                        continue;
                    }
                } catch (URISyntaxException e) {
                    // Ignore invalid URLs
                    continue;
                }
                if (!visitedPages.contains(nextPageUrl)) {
                    scrape(nextPageUrl);
                }
            }
        } catch (IOException e) {
            System.err.println("Error connecting to " + url + ": " + e.getMessage());
        } catch (URISyntaxException e) {
            System.err.println("Error parsing URL " + url + ": " + e.getMessage());
        }
    }




    public static JSONObject scrapePage(String url) throws IOException {
        Document document = Jsoup.connect(url).get();

        String title = document.title();
        System.out.println("Title: " + title);

        Elements headings = document.select("h1, h2, h3, h4, h5, h6");
        JSONArray headingsArray = new JSONArray();
        for (Element heading : headings) {
            headingsArray.put(heading.text());
        }

        Elements paragraphs = document.select("p");
        JSONArray paragraphsArray = new JSONArray();
        for (Element paragraph : paragraphs) {
            paragraphsArray.put(paragraph.text());
        }

        Elements keywords = document.select("meta[name=keywords]");
        JSONArray keywordsArray = new JSONArray();
        for (Element keyword : keywords) {
            keywordsArray.put(keyword.attr("content"));
        }

        Elements tables = document.select("table");
        JSONArray tablesArray = new JSONArray();
        for (Element table : tables) {
            tablesArray.put(table.outerHtml());
        }

        Elements anchors = document.select("a[href]");
        JSONArray anchorsArray = new JSONArray();
        for (Element anchor : anchors) {
            JSONObject anchorObject = new JSONObject();
            anchorObject.put("text", anchor.text());
            anchorObject.put("href", anchor.attr("abs:href")); // Use absolute URL
            anchorsArray.put(anchorObject);
        }

        JSONObject json = new JSONObject();
        json.put("title", title);
        json.put("headings", headingsArray);
        json.put("paragraphs", paragraphsArray);
        json.put("keywords", keywordsArray);
        json.put("tables", tablesArray);
        json.put("anchors", anchorsArray);

        return json;
    }
}
