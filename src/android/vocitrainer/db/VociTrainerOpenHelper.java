package android.vocitrainer.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class VociTrainerOpenHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "voci_trainer";

    public VociTrainerOpenHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE IF NOT EXISTS language (" +
                "name TEXT PRIMARY KEY" +
                ");");
        db.execSQL("CREATE TABLE IF NOT EXISTS lesson (" +
                "id INTEGER PRIMARY KEY," +
                "language TEXT," +
                "name," +
                "FOREIGN KEY (language) REFERENCES language(name)" +
                "UNIQUE (language, name)" +
                ");");
        db.execSQL("CREATE TABLE IF NOT EXISTS expression (" +
                "id INTEGER PRIMARY KEY," +
                "base_language TEXT," +
                "foreign_language TEXT," +
                "lesson_id INTEGER," +
                "FOREIGN KEY (lesson_id) REFERENCES lesson(id))");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }

}
