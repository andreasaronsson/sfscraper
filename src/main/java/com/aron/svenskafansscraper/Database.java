/**
 * 
 */
package com.aron.svenskafansscraper;

import java.io.File;

import com.almworks.sqlite4java.SQLite;
import com.almworks.sqlite4java.SQLiteConnection;
import com.almworks.sqlite4java.SQLiteException;

/**
 *
 */
class Database {

    static {
        java.util.logging.Logger.getLogger("com.almworks.sqlite4java").setLevel(java.util.logging.Level.WARNING); 
    }
    
    static SQLiteConnection connection() {
        
        SQLiteConnection db = new SQLiteConnection(new File(System.getProperty("sf.db")));
        try {
            SQLite.loadLibrary();
            db.open();
        } catch (SQLiteException e) {
            e.printStackTrace();
        }
        return db;
    }

}
