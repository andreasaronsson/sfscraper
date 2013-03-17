/**
 * 
 */
package com.aron.svenskafansscraper;

import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 *
 */
class SvenskaFans {

    /**
     * @param args
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {
        // TODO Auto-generated method stub
        String urlString = "http://www.svenskafans.com/fotboll/ifkgoteborg/forum.aspx";

        Document document = Jsoup.connect(urlString).get();
//        Elements posts = document.getElementsByClass("m_cont");

//        for (Element elem : posts) {
//            // System.err.println(elem.text());
//            // Element elem = elems.get(1);
//            String name = elem.select("a").first().text();
//            System.err.println(name);
//            String sent = elem.select("b").get(1).text();
//            System.err.println(sent);
//            String post = elem.getElementsByClass("m_in").text();
//
//            System.err.println(post); // TODO:separate the quoted posts. 
//            System.err.println("\n\n\n");
//        }
        
        // TODO: Dig out link tip... 
        Element linkTipsHeader = document.getElementsByClass("ls_top_news_header_end_not_sl").get(1);
        for (Element linkRefs : linkTipsHeader.siblingElements()) {
            for (Element linkRef : linkRefs.select("a")) {
                System.err.println(linkRef.toString());
            }
        }
    }

}
