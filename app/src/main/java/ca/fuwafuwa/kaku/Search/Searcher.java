package ca.fuwafuwa.kaku.Search;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import org.jetbrains.annotations.NotNull;

import java.sql.SQLException;
import java.util.List;

import ca.fuwafuwa.kaku.Database.JmDictDatabase.Models.EntryOptimized;

/**
 * Created by 0xbad1d3a5 on 8/28/2016.
 */
public class Searcher implements JmTask.SearchJmTaskDone {

    public interface SearchDictDone
    {
        void jmResultsCallback(List<JmSearchResult> results, SearchInfo search);
    }

    private static final String TAG = Searcher.class.getName();

    private SearchDictDone mSearchDictDone;
    private Context mContext;

    public Searcher(Context context) throws SQLException
    {
        mContext = context;
    }

    public void registerCallback(SearchDictDone dictDone)
    {
        this.mSearchDictDone = dictDone;
    }

    public void unregisterCallback()
    {
        this.mSearchDictDone = null;
    }

    public void search(SearchInfo searchInfo)
    {
        try {
            new JmTask(searchInfo, this, mContext).executeOnExecutor(AsyncTask.SERIAL_EXECUTOR);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void jmTaskCallback(@NotNull List<JmSearchResult> results, @NotNull SearchInfo searchInfo)
    {
        mSearchDictDone.jmResultsCallback(results, searchInfo);
    }
}
