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
import android.vocitrainer.db.LessonManager;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class Lesson extends ListActivity {
    
    private LessonManager lessonManager;
    private ArrayAdapter<String> lessonAdapter;
    protected String language;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle extras = getIntent().getExtras();
        language = (String) extras.get("language");
        lessonManager = new LessonManager(getApplicationContext());
        lessonAdapter = new ArrayAdapter<String>(this, R.layout.list_item, lessonManager.getLessons(language));
        setListAdapter(lessonAdapter);
        ListView lv = getListView();
        lv.setTextFilterEnabled(true);
        
        lv.setOnItemClickListener(new OnItemClickListener() {

            public void onItemClick(AdapterView<?> parent, View view,
                    int position, long id) {
                Intent i = new Intent(getApplicationContext(), Lesson.class);
                i.putExtra("language", Lesson.this.language);
                i.putExtra("lesson", ((TextView) view).getText());
                startActivity(i);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.add_lesson, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()) {
        case R.id.addLesson:
            showDialog(10);
            break;
        }
        return true;
    }

    @Override
    protected Dialog onCreateDialog(int id) {
        switch(id) {
        case 10:
            showAddLessonDialog();
        }
        return super.onCreateDialog(id);
    }

    private void showAddLessonDialog() {
        final EditText input = new EditText(this);
        Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(R.string.addNewLesson)
        .setView(input)
        .setCancelable(true)
        .setPositiveButton("OK", new DialogInterface.OnClickListener() {

            public void onClick(DialogInterface dialog, int which) {
                String lesson = input.getText().toString();
                if (lessonManager.addLesson(lesson, Lesson.this.language)){
                    lessonAdapter.add(lesson);
                    lessonAdapter.notifyDataSetChanged();
                } else {
                    dialog.dismiss();
                    Toast.makeText(getApplicationContext(), R.string.couldNotAddLesson, Toast.LENGTH_LONG).show();
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
