package cl.cutiko.databaseoverlord.database;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

/**
 * Created by cutiko on 09-03-17.
 */

public class PendingReader extends PendingTransactor<Object,Object,Long> {

    public PendingReader(Context context) {
        super(context);
    }

    @Override
    protected Long doInBackground(Object... params) {
        long start = System.currentTimeMillis();
        SQLiteDatabase database = openHelper.getReadableDatabase();
        String select = "SELECT * FROM " + PendingOpenHelper.PENDING_TABLE + " WHERE " + PendingOpenHelper.STATUS_COLUMN + " = ?";
        String[] arguments = {"FALSE"};
        try {
            Cursor cursor = database.rawQuery(select, arguments);
            if (cursor != null) {
                cursor.close();
                long end = System.currentTimeMillis();
                long diff = end - start;
                return diff;
            } else {
                return 0L;
            }
        } catch (SQLException e) {
            Log.d(getClass().getSimpleName(), e.getMessage());
            return 0L;
        }
        //database.close(); ... is not closed :(
    }
}
