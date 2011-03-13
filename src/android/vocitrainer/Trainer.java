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
import android.widget.TextView;
import android.widget.Toast;

public class Trainer extends ListActivity {
    /** Called when the activity is first created. */
    
    private ArrayAdapter<String> languageAdapter;
    private LanguageManager languageManager;
    
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        languageManager = new LanguageManager(getApplicationContext());
        languageAdapter = new ArrayAdapter<String>(this, R.layout.list_item, languageManager.getLanguages());
        setListAdapter(languageAdapter);
        ListView lv = getListView();
        lv.setTextFilterEnabled(true);
        
        lv.setOnItemClickListener(new OnItemClickListener() {

            public void onItemClick(AdapterView<?> parent, View view,
                    int position, long id) {
                Intent i = new Intent(getApplicationContext(), Lesson.class);
                i.putExtra("language", ((TextView)view).getText());
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
        switch(item.getItemId()) {
        case R.id.add_language:
            showDialog(10);
            break;
        }
        return true;
    }

    @Override
    protected Dialog onCreateDialog(int id) {
        switch(id) {
        case 10:
            showAddLanguageDialog();
        }
        return super.onCreateDialog(id);
    }

    private void showAddLanguageDialog() {
        final EditText input = new EditText(this);
        Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(R.string.addNewLanguage)
        .setView(input)
        .setCancelable(true)
        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                String lang = input.getText().toString();
                if (languageManager.addLanguage(lang)){
                    languageAdapter.add(lang);
                    languageAdapter.notifyDataSetChanged();
                } else {
                    dialog.dismiss();
                    Toast.makeText(getApplicationContext(), R.string.couldNotAddLanguage, Toast.LENGTH_LONG).show();
                }
            }
        })
        .setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        AlertDialog dialog = builder.create();
        dialog.show();
    }
}
