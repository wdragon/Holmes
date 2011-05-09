package com.holmes.storage;

import java.util.List;

import android.content.Context;

/**
 * This class is used for handling local storage using Sqlite.
 * Users should use "get" and "put" to access the data.
 */
public class LocalStorage {

  // The actual sqlite database.
  private LocalDatabaseHelper helper;
  
  public LocalStorage(Context context) {
    this.helper = new LocalDatabaseHelper(context);
  }
  
  /**
   * This class represents each entry in the storage.
   */
  public static class StorageEntry {
    String user;
    long timeStampInSec;
    String category;
    byte[] message;
    StorageEntry(String user, long timeStampInSec, String category, byte[] message) {
      this.user = user;
      this.timeStampInSec = timeStampInSec;
      this.category = category;
      this.message = message;
    }
  }
  
  /**
   * Add a new entry into the storage.
   */
  public void put(StorageEntry entry) {
    helper.put(entry.user, entry.timeStampInSec, entry.category, entry.message);
  }

  /**
   * Query a list of entries from the storage.f  
   * @param user     The user name string, null means all users.
   * @param timeStampInSecStart   The start ts (included), null means no start time.
   * @param timeStampInSecEnd     The end ts (includes), null means no end time.
   * @param category    The category name string, null means all cactegories
   * @return         The list of entries.
   */
  public List<StorageEntry> get(String user, Long timeStampInSecStart, Long timeStampInSecEnd, String category) {
    return helper.get(user, timeStampInSecEnd, timeStampInSecEnd, category);
  }

  /**
   * For mock test.
   */
  public void testPut() {
    put(new StorageEntry("testuser",
        System.currentTimeMillis() / 1000,
        "testcategory",
        "TestMessage".getBytes()));
  }
  
  /**
   * For mock test.
   */
  public String testGet() {
    List<StorageEntry> entries = get("testuser", null, null, "testcategory");
    if (entries.size() == 0) {
      return "No entries found";
    }
    StringBuilder result = new StringBuilder();
    for(StorageEntry entry: entries) {
      result.append("[user=" + entry.user
          + " ts=" + entry.timeStampInSec
          + " category=" + entry.category
          + " message=" + new String(entry.message)
          + "]  ");
    }
    return result.toString();    
  }
}
