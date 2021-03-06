/**
 * 
 */
package com.aron.svenskafansscraper;

import java.io.IOException;
import java.net.SocketTimeoutException;
import java.util.LinkedList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 *
 */
class PostScraper {
    private final static String SVENSKA_FANS_FORUM_URL = "http://www.svenskafans.com/fotboll/ifkgoteborg/forum.aspx";
    private List<Post> posts = new LinkedList<Post>();

    List<Post> scrape() {
        Document document;
        try {
            document = Jsoup.connect(SVENSKA_FANS_FORUM_URL).get();
            Elements elements = document.getElementsByClass("m_cont");

            // elem.text() <- this gives all of the post - with quotes
            for (Element elem : elements) {
                String name = elem.select("a").first().text();
                String sent = elem.select("b").get(1).text();
                String post = elem.getElementsByClass("m_in").outerHtml();
                Post p = new Post(name, sent, post);
                posts.add(p);
            }
        } catch (SocketTimeoutException ste) {
            // These are spurious. Ignore.
        } catch (IOException e) {
            e.printStackTrace();
        }
        return posts;
    }

    public static void main(String[] args) {
        List<Post> posts = new PostScraper().scrape();
        for (Post p : posts) {
            System.err.println(p.getPost());
            System.err.println();
        }
    }
}
