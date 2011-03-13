package android.vocitrainer.db;

import android.content.Context;

public class ExpressionManager {

    private VociTrainerOpenHelper dbHelper;
    private String tableName;

    public ExpressionManager(Context context) {
        dbHelper = new VociTrainerOpenHelper(context);
        tableName = "expression";
    }

    public String[] getExpressions(String language, String lesson) {
        // TODO Auto-generated method stub
        return new String[]{};
    }
}
