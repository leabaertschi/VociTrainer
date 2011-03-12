package android.vocitrainer;

import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.app.Dialog;
import android.app.ListActivity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.vocitrainer.db.LanguageManager;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

public class Trainer extends ListActivity {
    /** Called when the activity is first created. */
    
    static final String[] LANGUAGES = new String[] {"Deutsch", "Svenska"};
    
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LanguageManager lm = new LanguageManager(getApplicationContext());
        setListAdapter(new ArrayAdapter<String>(this, R.layout.list_item, lm.getLanguages()));
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
    
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.add_language, menu);
        return true;
    }
    
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        System.err.println(item);
        switch(item.getItemId()) {
        case R.id.add_language:
            showDialog(10);
            break;
        default:
            Toast.makeText(this, "Just a test", Toast.LENGTH_SHORT).show();
        }
        return true;
    }
    
    @Override
    protected Dialog onCreateDialog(int id) {
        switch(id) {
        case 10:
            EditText input = new EditText(this);
            Builder builder = new AlertDialog.Builder(this);
            builder.setMessage(R.string.addNewLanguage)
            .setView(input)
            .setCancelable(true)
            .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    
                }
            })
            .setNegativeButton("No", new DialogInterface.OnClickListener() {
                
                public void onClick(DialogInterface dialog, int which) {
                    Toast.makeText(getApplicationContext(), "Activity will continue", Toast.LENGTH_SHORT).show();
                    
                }
            });
            AlertDialog dialog = builder.create();
            dialog.show();
        }
        return super.onCreateDialog(id);
    }
}