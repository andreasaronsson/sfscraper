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
        LinkScraper.scrape();
        List<Post> posts = new PostScraper().scrape();

        for (Post p : posts) {
            if (!exists(db, p)) {
                persist(db, p);
            }
        }
        db.dispose();
    }

    /**
     * @param db
     * @param p
     * @return
     */
    private static boolean exists(SQLiteConnection db, Post p) {
        SQLiteStatement st;
        boolean exist = false;
        try {
            String query = String.format("SELECT * FROM 'posts' WHERE hash=%d;", p.hashCode());
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
    private static void persist(SQLiteConnection db, Post p) {
        SQLiteStatement st;
        try {
            String query = String.format("INSERT INTO 'posts' (name, post, hash, sent) VALUES('%s', '%s', '%d', '%s');", p.getName(), p.getPost(),
                    p.hashCode(), p.getSent());
            st = db.prepare(query);
            st.stepThrough();
            st.dispose();
        } catch (SQLiteException e) {
            e.printStackTrace();
        }
    }

}
