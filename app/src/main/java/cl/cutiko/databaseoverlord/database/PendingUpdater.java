package cl.cutiko.databaseoverlord.database;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by cutiko on 09-03-17.
 */

public class PendingUpdater extends PendingTransactor<Integer, Integer, Boolean> {


    public PendingUpdater(Context context) {
        super(context);
    }

    @Override
    protected Boolean doInBackground(Integer... params) {
        SQLiteDatabase database = openHelper.getWritableDatabase();
        if (database != null) {
            if (params != null) {
                try {
                    //Yes, you could use database.update() just felt like writing the query
                    database.execSQL(query(params[0]));
                    return true;
                } catch (SQLException e) {
                    return false;
                }
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    private String query(int id){
        return "UPDATE " + PendingOpenHelper.PENDING_TABLE + " SET " + PendingOpenHelper.STATUS_COLUMN +  " = 'TRUE' WHERE ID = " + id;
    }
}
