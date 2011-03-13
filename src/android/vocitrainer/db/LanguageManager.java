package android.vocitrainer.db;

import java.util.ArrayList;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class LanguageManager {
    
    private VociTrainerOpenHelper dbHelper;
    private final String tableName;
    
    public LanguageManager(Context context) {
        dbHelper = new VociTrainerOpenHelper(context);
        tableName = "language";
    }
    
    /**
     * Return all languages in the db
     * @return {@link ArrayList}<String> languages
     */
    public ArrayList<String> getLanguages() {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor c = db.query(tableName, null, null, null, null, null, null);
        ArrayList<String> langs = new ArrayList<String>();
        if (!c.moveToFirst()) {
            db.close();
            return langs;
        }
        langs.add(c.getString(0));
        while(c.moveToNext()) {
            langs.add(c.getString(0));
        }
        db.close();
        return langs;
    }
    
    /**
     * Add a new language to the db
     * @param language String
     * @return boolean true if the write was successful, false otherwise
     */
    public boolean addLanguage(String language) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("name", language);
        long res = db.insert(tableName, null, values);
        db.close();
        if (res == -1) {
            return false;
        }
        return true;
    }
}
