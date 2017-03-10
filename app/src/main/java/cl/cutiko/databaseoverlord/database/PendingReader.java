package cl.cutiko.databaseoverlord.database;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import cl.cutiko.databaseoverlord.models.Pending;

/**
 * Created by cutiko on 09-03-17.
 */

public class PendingReader extends PendingTransactor<Void, Integer, List<Pending>> {

    public PendingReader(Context context) {
        super(context);
    }

    @Override
    protected List<Pending> doInBackground(Void... params) {
        List<Pending> pendings = new ArrayList<>();
        SQLiteDatabase database = openHelper.getReadableDatabase();
        String select = "SELECT * FROM " + PendingOpenHelper.PENDING_TABLE + " WHERE " + PendingOpenHelper.STATUS_COLUMN + " = ?";
        String[] arguments = {"FALSE"};
        Cursor cursor = database.rawQuery(select, arguments);
        if (cursor != null) {
            for (int i = 0; i < cursor.getCount(); i++) {
                cursor.moveToPosition(i);
                int id = Integer.parseInt(cursor.getString(0));
                String name = cursor.getString(1);
                boolean status = cursor.getString(2).equals("TRUE");
                Pending pending = new Pending(id, name, status);
                pendings.add(pending);
                Log.d("PENDING", pending.getId() + " " + pending.getName() + " " + pending.getStatus());
            }
        }
        return pendings;
    }
}
