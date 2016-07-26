package fooddelivery.fooddelivery;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.CursorAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

public class FoodCategoryActivity extends Activity {

    public static final String EXTRA_CATEGORY_NO = "categoryNo";
    private SQLiteDatabase db;
    private Cursor categoriesCursor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categeory_restaurants);
        int categoryNo = (Integer) getIntent().getExtras().get(EXTRA_CATEGORY_NO);

        //Populate the list_favorites ListView from a cursor

        ListView listCategories = (ListView) findViewById(R.id.list_restaurants);
        try {
            SQLiteOpenHelper starbuzzDatabaseHelper = new StarbuzzDatabaseHelper(this);
            db = starbuzzDatabaseHelper.getReadableDatabase();
            categoriesCursor = db.query("RESTAURANT_CATEGORY",
                    new String[]{"_id", "RESTAURANT_ID"}, "CATEGORY_ID = ?",
                    new String[]{Integer.toString(categoryNo)},
                    null, null, null);
            CursorAdapter categoryAdapter =
                    new SimpleCursorAdapter(FoodCategoryActivity.this,
                            android.R.layout.simple_list_item_1,
                            categoriesCursor,
                            new String[]{"RESTAURANT_ID"},
                            new int[]{android.R.id.text1}, 0);
            listCategories.setAdapter(categoryAdapter);
        } catch (SQLiteException e) {
            Toast toast = Toast.makeText(this, "Database unavailable3", Toast.LENGTH_SHORT);
            toast.show();
        }

        //Navigate to DrinkActivity if a drink is clicked
        listCategories.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> listView, View v, int position, long id) {
                Intent intent = new Intent(FoodCategoryActivity.this, FoodActivity.class);
                intent.putExtra(FoodActivity.EXTRA_FOODNO, (int) id);
                intent.putExtra(FoodActivity.EXTRA_RESTAURANTNO, (int) id);
                startActivity(intent);
            }
        });
    }

    //Close the cursor and database in the onDestroy() method
    @Override
    public void onDestroy() {
        super.onDestroy();
        if (categoriesCursor != null)
            categoriesCursor.close();
        if (db != null)
            db.close();
    }
}
