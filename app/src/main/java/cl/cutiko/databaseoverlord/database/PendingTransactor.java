package cl.cutiko.databaseoverlord.database;

import android.content.Context;
import android.os.AsyncTask;

/**
 * Created by cutiko on 10-03-17.
 */

public abstract class PendingTransactor<Param, Progress, ResultObject> extends AsyncTask<Param, Progress, ResultObject> {

    protected final PendingOpenHelper openHelper;

    public PendingTransactor(Context context) {
        openHelper = new PendingOpenHelper(context);
    }


}
