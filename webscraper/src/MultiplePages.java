import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.FileWriter;
import java.io.IOException;
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
            String filename = url.replaceAll("[^a-zA-Z0-9.-]", "_") + ".json";
            FileWriter file = new FileWriter(filename);
            file.write(data.toString(4));
            file.flush();
            file.close();

            JSONArray anchors = data.getJSONArray("anchors");
            for (int i = 0; i < anchors.length(); i++) {
                JSONObject anchor = anchors.getJSONObject(i);
                String href = anchor.getString("href");
                String nextPageUrl = BASE_URL + href;
                if (!visitedPages.contains(nextPageUrl)) {
                    scrape(nextPageUrl);
                }
            }
        } catch (IOException e) {
            System.err.println("Error connecting to " + url + ": " + e.getMessage());
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
            anchorObject.put("href", anchor.attr("href"));
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
