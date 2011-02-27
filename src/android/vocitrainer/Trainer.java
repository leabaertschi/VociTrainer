package android.vocitrainer;

import java.util.ArrayList;

import android.app.ListActivity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.vocitrainer.db.VociTrainerOpenHelper;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class Trainer extends ListActivity {
    /** Called when the activity is first created. */
    
    static final String[] LANGUAGES = new String[] {"Deutsch", "Svenska"};
    private VociTrainerOpenHelper dbHelper;
    
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        dbHelper = new VociTrainerOpenHelper(getApplicationContext());
        setListAdapter(new ArrayAdapter<String>(this, R.layout.list_item, getLanguages()));
        ListView lv = getListView();
        lv.setTextFilterEnabled(true);
        
        lv.setOnItemClickListener(new OnItemClickListener() {

            public void onItemClick(AdapterView<?> parent, View view,
                    int position, long id) {
                Intent i = new Intent(getApplicationContext(), Lesson.class);
                startActivity(i);
            }
        });
    }
    
    private ArrayList<String> getLanguages() {
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
}