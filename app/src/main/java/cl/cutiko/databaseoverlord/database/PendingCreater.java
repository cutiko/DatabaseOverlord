package cl.cutiko.databaseoverlord.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;

import java.util.List;

import cl.cutiko.databaseoverlord.models.Pending;

/**
 * Created by cutiko on 09-03-17.
 */

public class PendingCreater extends AsyncTask<List<Pending>, Integer, Boolean> {

    private final PendingOpenHelper pendingOpenHelper;

    public PendingCreater(Context context) {
        pendingOpenHelper = new PendingOpenHelper(context);
    }

    @Override
    protected Boolean doInBackground(List<Pending>... params) {
        if (params != null) {
            List<Pending> pendings = params[0];
            if (pendings != null) {
                SQLiteDatabase database = pendingOpenHelper.getWritableDatabase();
                if (database != null) {
                    for (int i = 0; i < pendings.size(); i++) {
                        database.execSQL(insert(pendings.get(i)));
                        publishProgress(i);
                    }
                    database.close();
                    return true;
                } else {
                    return false;
                }
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    private String insert(Pending pending){
        return "INSERT INTO " + PendingOpenHelper.PENDING_TABLE +
                " (" + PendingOpenHelper.NAME_COLUMN + ", " + PendingOpenHelper.STATUS_COLUMN +") " +
                "VALUES ('" + pending.getName() +"' , '" + String.valueOf(pending.getStatus()).toUpperCase() + "')";
    }
}
