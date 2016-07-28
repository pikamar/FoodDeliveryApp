package fooddelivery.fooddelivery;

import android.os.AsyncTask;
import android.util.Log;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class DBJSONFetchTask extends AsyncTask<Void,Void,List<Category>> {

    private static final String TAG = "DBJSONFetchTask";

    @Override
    protected List<Category> doInBackground(Void... params) {
        return new DBJSONFetcher().fetchItems();
    }

    @Override
    protected void onPostExecute(List<Category> categories) {
        // TODO add categories to DB
    }
}
