package crawler.factory;

import java.util.HashMap;
import java.util.Map;

import crawler.base.BaseCrawler;
import crawler.type.*;

public class CrawlerFactory {
    private Map<String, Class<? extends BaseCrawler>> crawlerMap;

    public CrawlerFactory() {
        crawlerMap = new HashMap<>();
        // Initialize the mapping of URLs to data crawler classes
        crawlerMap.put("https://example.com/relics", AttractionCrawler.class);
        crawlerMap.put("https://nguoikesu.com/dong-lich-su", DynastyCrawler.class);
        crawlerMap.put("", EventCrawler.class);
        crawlerMap.put(" ", FestivalCrawler.class);
        crawlerMap.put("https://nguoikesu.com/nhan-vat", FigureCrawler.class);
        crawlerMap.put("https://nguoikesu.com/di-tich-lich-su", RelicCrawler.class);

        // Add more URL mappings as needed
    }

    public BaseCrawler createCrawler(String url) throws IllegalAccessException, InstantiationException {
        Class<? extends BaseCrawler> crawlerClass = crawlerMap.get(url);
        if (crawlerClass != null) {
            BaseCrawler crawler = crawlerClass.newInstance();
            crawler.setUrl(url);
            return crawler;
        } else {
            throw new IllegalArgumentException("No crawler found for the given URL: " + url);
        }
    }

}
