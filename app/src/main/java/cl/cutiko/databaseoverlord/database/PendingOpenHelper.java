package cl.cutiko.databaseoverlord.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by cutiko on 09-03-17.
 */

public class PendingOpenHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    public static final String PENDING_TABLE = "PENDINGS";
    public static final String NAME_COLUMN = "name";
    public static final String STATUS_COLUMN = "status";
    public static final String PENDING_TABLE_CREATE = "CREATE TABLE " + PENDING_TABLE + " (id INTEGER PRIMARY KEY AUTOINCREMENT, "+ NAME_COLUMN + " TEXT, " + STATUS_COLUMN + " BOOLEAN)";
    private static final String DATABASE_NAME = "PENDING_DATABASE";


    public PendingOpenHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(PENDING_TABLE_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //TODO
    }

}
