package cl.cutiko.databaseoverlord.application;

import android.app.Application;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import cl.cutiko.databaseoverlord.database.PendingOpenHelper;

/**
 * Created by cutiko on 09-03-17.
 */

public class PendingApp extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        PendingOpenHelper pendingOpenHelper = new PendingOpenHelper(getApplicationContext());
        SQLiteDatabase db = pendingOpenHelper.getWritableDatabase();
        if (db != null) {
            Log.d("APPLICATION", "db is not null");
            db.close();
        } else {
            Log.d("APPLICATION", "db is null");
        }
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
    }
}
