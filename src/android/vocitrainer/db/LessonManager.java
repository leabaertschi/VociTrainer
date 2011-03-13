package android.vocitrainer.db;

import java.util.ArrayList;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class LessonManager {

    private VociTrainerOpenHelper dbHelper;
    private final String tableName;

    public LessonManager(Context context) {
        dbHelper = new VociTrainerOpenHelper(context);
        tableName = "lesson";
    }

    /**
     * Return all lessons for language
     * @param String language
     * @return {@link ArrayList}<String> lessons
     */
    public ArrayList<String> getLessons(String language) {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor c = db.query(
                tableName,
                new String[]{"name"},
                "language = ?",
                new String[] {language},
                null,
                null,
                null);
        ArrayList<String> lessons = new ArrayList<String>();
        if (!c.moveToFirst()) {
            return lessons;
        }
        lessons.add(c.getString(0));
        while(c.moveToNext()) {
            lessons.add(c.getString(0));
        }
        return lessons;
    }

    /**
     * Add a new lesson to the db
     * @param String lesson
     * @param String language
     * @return boolean true if the write was successful, false otherwise
     */
    public boolean addLesson(String lesson, String language) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("name", lesson);
        values.put("language", language);
        long res = db.insert(tableName, null, values);
        if (res == -1) {
            return false;
        }
        return true;
    }
}
