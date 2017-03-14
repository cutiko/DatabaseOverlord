package cl.cutiko.databaseoverlord.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import cl.cutiko.databaseoverlord.models.Pending;

/**
 * Created by cutiko on 09-03-17.
 */

public class PendingCreater extends PendingTransactor<Pending, Integer, String> {


    public PendingCreater(Context context) {
        super(context);
    }

    @Override
    protected String doInBackground(Pending... params) {
        long start = System.currentTimeMillis();
        SQLiteDatabase database = openHelper.getWritableDatabase();
        if (database != null) {
            for (int i = 0; i < 10000; i++) {
                Pending pending = new Pending(String.valueOf(i), false);
                try {
                    ContentValues contentValues = new ContentValues();
                    contentValues.put(PendingOpenHelper.NAME_COLUMN, pending.getName());
                    contentValues.put(PendingOpenHelper.STATUS_COLUMN, String.valueOf(pending.getStatus()).toUpperCase());
                    long id = database.insert(PendingOpenHelper.PENDING_TABLE, null, contentValues);
                    /*pending.setId((int) id);
                    pendings.add(pending);*/
                } catch (SQLException e) {
                    Log.d(getClass().getSimpleName(), e.getMessage());
                }
                //publishProgress(i);
            }
            database.close();
        }
        long end = System.currentTimeMillis();
        long diff = end - start;
        return "Tomó " + diff + " milisegundos hacer 10000 inserciones";
    }
}
