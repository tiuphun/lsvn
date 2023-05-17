// This scraper extracts data from webpages saved to the local machine. The pages are in `website` folder.
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import java.io.File;
import java.io.IOException;

public class LocalScraper {
    
    public static void main(String[] args) throws IOException {
        
        // Load local HTML file
        File input = new File("website/culture.html");
        Document document = Jsoup.parse(input, "UTF-8");
        
        // Extract title
        String title = document.title();
        System.out.println("Title: " + title);
        
        // Extract headings
        Elements headings = document.select("h1, h2, h3, h4, h5, h6");
        for (Element heading : headings) {
            System.out.println("Heading: " + heading.text());
        }
        
        // Extract table
        Element table = document.select("table").first();
        if (table != null) {
            System.out.println("\nTable:");
            Elements rows = table.select("tr");
            for (Element row : rows) {
                Elements columns = row.select("td");
                for (Element column : columns) {
                    System.out.print(column.text() + "\t");
                }
                System.out.println();
            }
        }

        // Extract anchor
        Elements links = document.select("a");
        for (Element link : links) {
            String text = link.text();
            System.out.println(text);
        }
    }
}
