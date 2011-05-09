package com.holmes.storage;

import java.util.ArrayList;
import java.util.List;

import com.holmes.storage.LocalStorage.StorageEntry;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * A helper class to manage local database creation (and upgrade
 * in the future).  Not accessible outside of this package.
 */
class LocalDatabaseHelper extends SQLiteOpenHelper {

  static final String dbName="holmesDB";
  
  public LocalDatabaseHelper(Context context) {
    super(context, dbName, null, 1);
  }

  /** 
   * Called by parent class.
   */
  @Override
  public void onCreate(SQLiteDatabase db) {
    db.execSQL("CREATE TABLE datatable (id PRIMARY KEY AUTOINCREMENT, " +
        "user VARCHAR(32), ts TIMESTAMP, category VARCHAR(32), message BLOB);");
  }

  /** 
   * Called by parent class.
   */
  @Override
  public void onUpgrade(SQLiteDatabase arg0, int arg1, int arg2) {
    // Do nothing for now.
  }
  
  /**
   * Called by user.
   */
  public void put(String user, long timeStampInSec, String category, byte[] message) {
    SQLiteDatabase db = getWritableDatabase();
    ContentValues values = new ContentValues();
    values.put("user", user);
    values.put("ts", timeStampInSec);
    values.put("category", category);
    values.put("message", message);
    if (-1 == db.insert("datatable", null, values)) {
      // throw new IOException("Error writing to local storage");
    }
  }
  
  /**
   * Called by user.
   */
  public List<StorageEntry> get(String user, Long timeStampInSecStart, Long timeStampInSecEnd, String category) {
    SQLiteDatabase db = getReadableDatabase();
    
    // convert selection criteria
    String selection = "";
    if (user != null) {
      selection = (selection == "") ? "" : selection + " AND ";
      selection = selection + "user = '" + user + "'";
    }
    if (timeStampInSecStart != null) {
      selection = (selection == "") ? "" : selection + " AND ";
      selection = selection + "ts >= " + timeStampInSecStart + "";
    }
    if (timeStampInSecStart != null) {
      selection = (selection == "") ? "" : selection + " AND ";
      selection = selection + "ts <= " + timeStampInSecEnd + "";
    }
    if (category != null) {
      selection = (selection == "") ? "" : selection + " AND ";
      selection = selection + "category = '" + category + "'";
    }
    
    // Query the db
    Cursor cursor = db.query("datatable",
        null /* all columns */,
        selection,
        null /* selection args */,
        null /* group by */,
        null /* having */,
        null /* orderBy */);

    List<StorageEntry> entries = new ArrayList<StorageEntry>();
    try {
      if (cursor.moveToFirst()) {
        int userCol = cursor.getColumnIndex("user");
        int tsCol = cursor.getColumnIndex("ts");
        int categoryCol = cursor.getColumnIndex("category");
        int messageCol = cursor.getColumnIndex("message");
        do {
          // Convert each row into an object.
          StorageEntry entry = new StorageEntry(
              cursor.getString(userCol),
              cursor.getLong(tsCol),
              cursor.getString(categoryCol),
              cursor.getBlob(messageCol)
              );
          entries.add(entry);
        } while (cursor.moveToNext());
      }
    } finally {
      cursor.close();
    }
    return entries;
  }
}
