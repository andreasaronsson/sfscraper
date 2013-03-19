/**
 * 
 */
package com.aron.svenskafansscraper;

import java.util.List;

import com.almworks.sqlite4java.SQLiteConnection;
import com.almworks.sqlite4java.SQLiteException;
import com.almworks.sqlite4java.SQLiteStatement;

/**
 *
 */
public class SvenskaFans {

    /**
     * @param args
     */
    public static void main(String[] args) {

        SQLiteConnection db = Database.connection();
        List<LinkTip> links = new LinkScraper().scrape();

        for (LinkTip tip : links) {
            if (!exists(db, createExistQuery(tip))) {
                persist(db, createPersistQuery(tip));
            }

        }

        List<Post> posts = new PostScraper().scrape();

        for (Post p : posts) {
            if (!exists(db, createExistQuery(p))) {
                persist(db, createPersistQuery(p));
            }
        }
        db.dispose();
    }

    private static String createExistQuery(Object o) {
        if (o instanceof Post) {
            return String.format("SELECT * FROM 'posts' WHERE hash=%d;", o.hashCode());
        }
        if (o instanceof LinkTip) {
            return String.format("SELECT * FROM 'links' WHERE hash=%d;", o.hashCode());
        }
        return null;
    }

    private static String createPersistQuery(Object o) {
        if (o instanceof Post) {
            Post p = (Post) o;
            return String.format("INSERT INTO 'posts' (name, post, hash, sent) VALUES('%s', '%s', '%d', '%s');", p.getName(), p.getPost(), p.hashCode(), p.getSent());
        }
        if (o instanceof LinkTip) {
            LinkTip tip = (LinkTip)o;
            return String.format("INSERT INTO 'links' (link, hash) VALUES('%s', '%d');", tip.getLink(), tip.hashCode());

        }
        return null;
    }

    /**
     * @param db
     * @param p
     * @return
     */
    private static boolean exists(SQLiteConnection db, String query) {
        SQLiteStatement st;
        boolean exist = false;
        try {
            st = db.prepare(query);
            exist = st.step();
            st.dispose();
        } catch (SQLiteException e) {
            e.printStackTrace();
        }
        return exist;
    }

    /**
     * @param db
     * @param p
     * @return
     */
    private static void persist(SQLiteConnection db, String query) {
        SQLiteStatement st;
        try {
            st = db.prepare(query);
            st.stepThrough();
            st.dispose();
        } catch (SQLiteException e) {
            e.printStackTrace();
        }
    }

}
