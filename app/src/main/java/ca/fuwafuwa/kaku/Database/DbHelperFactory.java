package ca.fuwafuwa.kaku.Database;

import android.content.Context;

import ca.fuwafuwa.kaku.Database.JmDictDatabase.JmDatabaseHelper;

/**
 * Created by 0xbad1d3a5 on 12/1/2016.
 */

public class DbHelperFactory {

    private static Context mContext;

    public DbHelperFactory(Context context){
        mContext = context;
    }

    public DatabaseHelper instance(Class clazz){
        if (clazz == JmDatabaseHelper.class) {
            return JmDatabaseHelper.instance(mContext);
        }
        else {
            return null;
        }
    }
}
