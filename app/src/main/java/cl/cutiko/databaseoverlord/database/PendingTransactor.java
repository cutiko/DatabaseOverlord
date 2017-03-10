package cl.cutiko.databaseoverlord.database;

import android.content.Context;
import android.os.AsyncTask;

/**
 * Created by cutiko on 10-03-17.
 */

abstract class PendingTransactor<Param, Progress, ResultObject> extends AsyncTask<Param, Progress, ResultObject> {

    final PendingOpenHelper openHelper;

    PendingTransactor(Context context) {
        openHelper = new PendingOpenHelper(context);
    }


}
