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
        
        SQLiteConnection db = new SQLiteConnection(new File("/home/aron/test"));
        try {
            SQLite.loadLibrary();
            db.open();
            
            
//          SQLiteStatement statement = db.prepare("SELECT name FROM sqlite_master WHERE type='table' ORDER BY name;");
//          boolean data = st.step();
//          System.err.println("Data? " + data);
        } catch (SQLiteException e) {
            e.printStackTrace();
        }
        return db;
    }

}
