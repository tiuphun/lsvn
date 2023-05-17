import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.json.JSONArray;
import org.json.JSONObject;
import java.io.FileWriter;
import java.io.IOException;

public class WebScraper {
    public static void main(String[] args) throws IOException {
        
        // Website to scrape from
        String url = "https://nguoikesu.com/"; 
        
        try {
            // Connect to the website and retrieve HTML content
            Document document = Jsoup.connect(url).get();
            
            // Extract page title
            String title = document.title();
            System.out.println("Title: " + title);
            
            // Extract all headings on the page
            Elements headings = document.select("h1, h2, h3, h4, h5, h6");
            JSONArray headingsArray = new JSONArray();
            for (Element heading : headings) {
                headingsArray.put(heading.text());
            }
            
            // Extract all paragraphs on the page
            Elements paragraphs = document.select("p");
            JSONArray paragraphsArray = new JSONArray();
            for (Element paragraph : paragraphs) {
                paragraphsArray.put(paragraph.text());
            }
            
            // Extract all keywords on the page
            Elements keywords = document.select("meta[name=keywords]");
            JSONArray keywordsArray = new JSONArray();
            for (Element keyword : keywords) {
                keywordsArray.put(keyword.attr("content"));
            }
            
            // Extract all tables on the page
            Elements tables = document.select("table");
            JSONArray tablesArray = new JSONArray();
            for (Element table : tables) {
                tablesArray.put(table.outerHtml());
            }
            
            // Extract all anchor items on the page
            Elements anchors = document.select("a[href]");
            JSONArray anchorsArray = new JSONArray();
            for (Element anchor : anchors) {
                JSONObject anchorObject = new JSONObject();
                anchorObject.put("text", anchor.text());
                anchorObject.put("href", anchor.attr("href"));
                anchorsArray.put(anchorObject);
            }
            
            // Create a JSON object with the extracted data
            JSONObject json = new JSONObject();
            json.put("title", title);
            json.put("headings", headingsArray);
            json.put("paragraphs", paragraphsArray);
            json.put("keywords", keywordsArray);
            json.put("tables", tablesArray);
            json.put("anchors", anchorsArray);
            
            // Format the JSON object with indentation
            String formattedJson = json.toString(4);
            
            // Write the formatted JSON object to a file
            FileWriter file = new FileWriter("output.json");
            file.write(formattedJson);
            file.flush();
            file.close();
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        
    }
}
