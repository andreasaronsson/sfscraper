/**
 * 
 */
package com.aron.svenskafansscraper;

import java.io.IOException;
import java.net.URLDecoder;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

/**
 *
 */
public class LinkScraper {
    private final static String SVENSKA_FANS_FORUM_URL = "http://www.svenskafans.com/fotboll/ifkgoteborg/forum.aspx";

    static void scrape() {
        try {
            Document document = Jsoup.connect(SVENSKA_FANS_FORUM_URL).get();
            Element linkTipsHeader = document.getElementsByClass("ls_top_news_header_end_not_sl").get(1);

            for (Element linkRefs : linkTipsHeader.siblingElements()) {
                for (Element linkRef : linkRefs.select("a")) {
                    String link = linkRef.attr("href");
                    if (link.length() > 5) {
                        String uri = link.substring(11);
                        
                        System.err.println(URLDecoder.decode(uri, "UTF-8"));
                        
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
