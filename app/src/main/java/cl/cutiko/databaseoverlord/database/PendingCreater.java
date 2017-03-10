package cl.cutiko.databaseoverlord.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import cl.cutiko.databaseoverlord.models.Pending;

/**
 * Created by cutiko on 09-03-17.
 */

public class PendingCreater extends PendingTransactor<Pending, Integer, List<Pending>> {


    public PendingCreater(Context context) {
        super(context);
    }

    @Override
    protected List<Pending> doInBackground(Pending... params) {
        List<Pending> pendings = new ArrayList<>();
        if (params != null) {
            SQLiteDatabase database = openHelper.getWritableDatabase();
            if (database != null) {
                int size = params.length;
                for (int i = 0; i < size; i++) {
                    Pending pending = params[i];
                    try {
                        ContentValues contentValues = new ContentValues();
                        contentValues.put(PendingOpenHelper.NAME_COLUMN, pending.getName());
                        contentValues.put(PendingOpenHelper.STATUS_COLUMN, String.valueOf(pending.getStatus()).toUpperCase());
                        long id = database.insert(PendingOpenHelper.PENDING_TABLE, null, contentValues);
                        pending.setId((int) id);
                        pendings.add(pending);
                    } catch (SQLException e) {
                        Log.d(getClass().getSimpleName(), e.getMessage());
                    }
                    publishProgress(i);
                }
                database.close();
            }
        }
        return pendings;
    }

    private String insert(Pending pending){
        return "INSERT INTO " + PendingOpenHelper.PENDING_TABLE +
                " (" + PendingOpenHelper.NAME_COLUMN + ", " + PendingOpenHelper.STATUS_COLUMN +") " +
                "VALUES ('" + pending.getName() +"' , '" + String.valueOf(pending.getStatus()).toUpperCase() + "')";
    }
}
