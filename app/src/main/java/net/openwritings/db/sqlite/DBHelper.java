package net.openwritings.db.sqlite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {

    private static final String dbName="mydatabase.db";
    private static final int dbVersion=1;

    public DBHelper(Context content){
        super(content, dbName, null, dbVersion);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query="CREATE TABLE Persons (_id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                                                        "firstname TEXT, age INTEGER)";
        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Upgrade.
    }
}
