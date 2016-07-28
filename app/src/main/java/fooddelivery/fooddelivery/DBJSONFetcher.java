package fooddelivery.fooddelivery;

import android.net.Uri;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class DBJSONFetcher {

    /**
     *
     * @param urlSpec
     * @return
     * @throws IOException
     */
    public byte[] getUrlBytes(String urlSpec) throws IOException {
        // Creates a URL object from a String
        URL url = new URL(urlSpec);
        // Get a connection represtentation ( it is not opened yet )
        HttpURLConnection connection = (HttpURLConnection)url.openConnection();
        try {
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            // Acquiring inputstream opens a connections
            InputStream in = connection.getInputStream();
            // 200 OK
            if (connection.getResponseCode() != HttpURLConnection.HTTP_OK) {
                throw new IOException(connection.getResponseMessage() +
                        ": with " +
                        urlSpec);
            }
            int bytesRead = 0;
            byte[] buffer = new byte[1024];
            while ((bytesRead = in.read(buffer)) > 0) {
                out.write(buffer, 0, bytesRead);
            }
            out.close();
            return out.toByteArray();
        } finally {
            // close connection (stream will be closed too)
            connection.disconnect();
        }
    }

    /**
     *  Converts bytes, fetched by getUtlBytes to string
     * @param urlSpec
     * @return
     * @throws IOException
     */
    public String getUrlString(String urlSpec) throws IOException {
        return new String(getUrlBytes(urlSpec));
    }

    public List<Category> fetchItems() {
        List<Category> items = new ArrayList<>();
        try {
            // URI builder is a convenient way to create and correctly parse url strings
            String url = Uri.parse("https://api.flickr.com/services/rest/")
                    .buildUpon()
                    .appendQueryParameter("method", "flickr.photos.getRecent")
                    .build().toString();

            String jsonString = getUrlString(url);
            Log.i("DBJSONFetcher", "Received JSON: " + jsonString);
            JSONArray jsonArray = new JSONArray(jsonString);
            parseItems(items, jsonArray);
        } catch (IOException ioe) {
            Log.e("DBJSONFetcher", "Failed to fetch items", ioe);
        } catch (JSONException je){
            Log.e("DBJSONFetcher", "Failed to parse JSON", je);
        }
        return items;
    }

    private void parseItems(List<Category> items, JSONArray jsonArray)
            throws IOException, JSONException {

        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject categoryJsonObject = jsonArray.getJSONObject(i);
            Category item = new Category();
            item.setId(categoryJsonObject.getLong("id"));
            item.setName(categoryJsonObject.getString("name"));
            items.add(item);
        }
    }

}
