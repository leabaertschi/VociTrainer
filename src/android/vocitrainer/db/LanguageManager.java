package android.vocitrainer.db;

import java.util.ArrayList;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class LanguageManager {
    
    private VociTrainerOpenHelper dbHelper;
    
    public LanguageManager(Context context) {
        dbHelper = new VociTrainerOpenHelper(context);
    }
    
    public ArrayList<String> getLanguages() {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor c = db.query("language", null, null, null, null, null, null);
        ArrayList<String> langs = new ArrayList<String>();
        if (c.getCount() <= 0) {
            return langs;
        }
        langs.add(c.getString(0));
        while(c.moveToNext()) {
            langs.add(c.getString(0));
        }
        return langs;
    }
    
    public void addLanguage(String langauge) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
    }
}
