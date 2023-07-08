package crawler.type;

import crawler.base.BaseCrawler;
import crawler.source.*;

public class AttractionCrawler extends BaseCrawler {
    private WikiCrawler wikiCrawler;
    private NguoikesuCrawler ngkesuCrawler;

    public AttractionCrawler() {
        wikiCrawler = new WikiCrawler();
        ngkesuCrawler = new NguoikesuCrawler();
    }

    @Override
    public void crawlData() {
        String url = getUrl();
        if (isFromWiki(url)) {
            wikiCrawler.crawlAttractionData();
        } else if (isFromNguoiKeSu(url)) {
            ngkesuCrawler.crawlAttractionData();
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
