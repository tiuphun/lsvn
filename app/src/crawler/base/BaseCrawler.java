package crawler.base;

public abstract class BaseCrawler {
    protected String url;

    public String getUrl() {
        return url;
    }
    public void setUrl(String url) {
        this.url = url;
    }

    public abstract void crawlData();
    public abstract void saveDataToJson();

    protected boolean isFromWiki(String url) {
        return url.startsWith("https://vi.wikipedia.org/");
    }

    protected boolean isFromNguoiKeSu(String url) {
        return url.startsWith("https://nguoikesu.com/");
    }
}
