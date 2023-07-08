package crawler.type;

import crawler.base.BaseCrawler;
import crawler.source.NguoikesuCrawler;
import crawler.source.WikiCrawler;

public class DynastyCrawler extends BaseCrawler {
    private WikiCrawler wikiCrawler;
    private NguoikesuCrawler ngkesuCrawler;

    public DynastyCrawler() {
        wikiCrawler = new WikiCrawler();
        ngkesuCrawler = new NguoikesuCrawler();
    }

    @Override
    public void crawlData() {
        String url = getUrl();

        if (isFromWiki(url)) {
            wikiCrawler.crawlDynastyData();
        } else if (isFromNguoiKeSu(url)) {
            ngkesuCrawler.crawlDynastyData();
        } else {
            System.out.println("Error: The provided URL is not supported.");
        }
    }

    @Override
    public void saveDataToJson() {
        // Specific implementation for saving attraction data to JSON
        // You can customize this method to format the data and store it in a JSON file
        // ...
    }
}
