package fooddelivery.fooddelivery;

import android.app.Activity;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    TextView etResponse;
    TextView tvIsConnected;
    StarbuzzDatabaseHelper databaseHelper;

    ProgressBar pbUpdate;

    Button btnUpdate;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // get reference to the views
        etResponse = (TextView) findViewById(R.id.etResponse);
        pbUpdate=(ProgressBar)findViewById(R.id.progressBar);
        tvIsConnected = (TextView) findViewById(R.id.tvIsConnected);
        btnUpdate = (Button)findViewById(R.id.btnUpdate);
        Button btn = (Button)findViewById(R.id.button2);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, TopLevelActivity.class);
                startActivity(intent);
            }
        });

        databaseHelper = new StarbuzzDatabaseHelper(this);

        checkConnection();

    }

    private boolean checkConnection(){
        // check if you are connected or not
        if(isConnected()){
            tvIsConnected.setBackgroundColor(0xFF00CC00);
            tvIsConnected.setText("You are connected");
            return true;
        }
        else{
            tvIsConnected.setBackgroundColor(0xFF0000);
            tvIsConnected.setText("You are NOT connected");
            return false;
        }
    }

    public void btnUpdateClicked(View view){
        pbUpdate.setProgress(0);
        if(!checkConnection())
            // FIXME tell that you need to be connected
            return;

        // call AsynTask to perform network operation on separate thread
//        new CategoriesAsyncTask().execute("http://www.mocky.io/v2/5799a988100000e2199e8316");
        // Categories
        new JRESTAsyncTask(new AsyncResponse() {
            @Override
            public void processFinish(String output) {
                // Parse JSON and etc
                databaseHelper.insertCategories(JSONAnalyzr.getCategories(output));
                pbUpdate.setProgress(pbUpdate.getProgress()+33);
                updFinished();
            }
        }).execute("http://www.mocky.io/v2/579afa0b1100008715cb76bc");
        // RestaurantCategory
        new JRESTAsyncTask(new AsyncResponse() {
            @Override
            public void processFinish(String output) {
                // Parse JSON and etc
                databaseHelper.insertRestaurantCategories(JSONAnalyzr.getRestaurantCategories(output));
                pbUpdate.setProgress(pbUpdate.getProgress()+33);
                updFinished();
            }
        }).execute("http://www.mocky.io/v2/579af8c51100006a15cb76b9");
        // Restaurants
        new JRESTAsyncTask(new AsyncResponse() {
            @Override
            public void processFinish(String output) {
                // Parse JSON and etc
                databaseHelper.insertRestaurants(JSONAnalyzr.getRestaurants(output));
                pbUpdate.setProgress(pbUpdate.getProgress()+33);
                updFinished();
            }
        }).execute("http://www.mocky.io/v2/579af7541100006515cb76b6");
    }

    public void updFinished(){
        if(pbUpdate.getProgress()==99){
            Toast toast = Toast.makeText(this, "Update Finished!", Toast.LENGTH_SHORT);
            toast.show();
        }
    }

    public boolean isConnected(){
        ConnectivityManager connMgr = (ConnectivityManager) getSystemService(Activity.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
        if (networkInfo != null && networkInfo.isConnected())
            return true;
        else
            return false;
    }

    // you may separate this or combined to caller class.
    private interface AsyncResponse {
        void processFinish(String output);
    }


    class JRESTAsyncTask extends AsyncTask<String, Void, String> {


        public AsyncResponse delegate = null;

        public JRESTAsyncTask(AsyncResponse delegate){
            this.delegate = delegate;
        }


        @Override
        protected String doInBackground(String... urls) {
            return GET(urls[0]);
        }

        // onPostExecute displays the results of the AsyncTask.
        @Override
        protected void onPostExecute(String result) {
            delegate.processFinish(result);
        }

        private String GET(String url){
            InputStream inputStream = null;
            String result = "";
            try {

                // create HttpClient
                HttpClient httpclient = new DefaultHttpClient();

                // make GET request to the given URL
                HttpResponse httpResponse = httpclient.execute(new HttpGet(url));

                // receive response as inputStream
                inputStream = httpResponse.getEntity().getContent();

                // convert inputstream to string
                if(inputStream != null)
                    result = convertInputStreamToString(inputStream);
                else
                    result = "Did not work!";

            } catch (Exception e) {
                Log.d("InputStream", e.getLocalizedMessage());
            }

            return result;
        }

        private String convertInputStreamToString(InputStream inputStream) throws IOException {
            BufferedReader bufferedReader = new BufferedReader( new InputStreamReader(inputStream));
            String line = "";
            String result = "";
            while((line = bufferedReader.readLine()) != null)
                result += line;

            inputStream.close();
            Log.i("convert", result);
            return result;

        }

    }


}
