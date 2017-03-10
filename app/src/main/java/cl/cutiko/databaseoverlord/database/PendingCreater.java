package cl.cutiko.databaseoverlord.database;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import cl.cutiko.databaseoverlord.models.Pending;

/**
 * Created by cutiko on 09-03-17.
 */

public class PendingCreater extends PendingTransactor<Pending, Integer, Integer> {


    public PendingCreater(Context context) {
        super(context);
    }

    @Override
    protected Integer doInBackground(Pending... params) {
        if (params != null) {
            SQLiteDatabase database = openHelper.getWritableDatabase();
            if (database != null) {
                int errors = 0;
                int size = params.length;
                for (int i = 0; i < size; i++) {
                    try {
                        database.execSQL(insert(params[i]));
                    } catch (SQLException e) {
                        errors++;
                    }
                    publishProgress(i);
                }
                database.close();
                return size - errors;
            } else {
                return 0;
            }
        } else {
            return 0;
        }
    }

    private String insert(Pending pending){
        return "INSERT INTO " + PendingOpenHelper.PENDING_TABLE +
                " (" + PendingOpenHelper.NAME_COLUMN + ", " + PendingOpenHelper.STATUS_COLUMN +") " +
                "VALUES ('" + pending.getName() +"' , '" + String.valueOf(pending.getStatus()).toUpperCase() + "')";
    }
}
