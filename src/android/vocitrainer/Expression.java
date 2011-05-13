package android.vocitrainer;

import android.app.ListActivity;
import android.os.Bundle;
import android.vocitrainer.db.ExpressionManager;
import android.widget.ArrayAdapter;

public class Expression extends ListActivity {
    private String language;
    private String lesson;
    private ArrayAdapter<String> expressionAdapter;
    private ExpressionManager expressionManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle extras = getIntent().getExtras();
        language = (String) extras.get("language");
        lesson = (String) extras.get("lesson");
        expressionManager = new ExpressionManager(getApplicationContext());
        expressionAdapter = new ArrayAdapter<String>(this, R.layout.list_item, expressionManager.getExpressions(language, lesson));
        setListAdapter(expressionAdapter);
//        ListView lv = getListView();
//        lv.setTextFilterEnabled(true);
//        
//        lv.setOnItemClickListener(new OnItemClickListener() {
//
//            public void onItemClick(AdapterView<?> parent, View view,
//                    int position, long id) {
//                Intent i = new Intent(getApplicationContext(), Lesson.class);
//                i.putExtra("language", Lesson.this.language);
//                i.putExtra("lesson", ((TextView) view).getText());
//                startActivity(i);
//            }
//        });
    }
}
