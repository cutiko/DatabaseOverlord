package cl.cutiko.databaseoverlord.views;

import cl.cutiko.databaseoverlord.models.Pending;

/**
 * Created by cutiko on 10-03-17.
 */

public interface CreateCallback {

    void created(Pending pending);

}
