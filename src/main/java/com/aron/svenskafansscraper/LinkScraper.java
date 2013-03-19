/**
 * 
 */
package com.aron.svenskafansscraper;

import java.io.IOException;
import java.net.URLDecoder;
import java.util.LinkedList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

/**
 *
 */
public class LinkScraper {
    private final static String SVENSKA_FANS_FORUM_URL = "http://www.svenskafans.com/fotboll/ifkgoteborg/forum.aspx";
    private List<LinkTip> links = new LinkedList<LinkTip>();

    List<LinkTip> scrape() {
        try {
            Document document = Jsoup.connect(SVENSKA_FANS_FORUM_URL).get();
            Element linkTipsHeader = document.getElementsByClass("ls_top_news_header_end_not_sl").get(1);

            for (Element linkRefs : linkTipsHeader.siblingElements()) {
                for (Element linkRef : linkRefs.select("a")) {
                    String link = linkRef.attr("href");
                    if (link.length() > 5) {
                        String uri = link.substring(11);
                        String decodedLink = URLDecoder.decode(uri, "UTF-8");
                        links.add(new LinkTip(decodedLink));
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return links;
    }
}
