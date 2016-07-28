package fooddelivery.fooddelivery;

import android.app.Activity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.util.Log;
import android.widget.CursorAdapter;
import android.widget.ImageView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;
import android.widget.Toast;

public class FoodActivity extends Activity {

    //public static final String EXTRA_FOODNO = "foodNo";
    public static final String EXTRA_RESTAURANTNO = "restaurantNo";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food);
        
        //Get the food from the intent
        /*int foodNo = (Integer)getIntent().getExtras().get(EXTRA_FOODNO);
        Food food = Food.FOODs[foodNo];

        //Populate the food image
        ImageView photo = (ImageView)findViewById(R.id.photo);
        photo.setImageResource(food.getImageResourceId());
        photo.setContentDescription(food.getName());

        //Populate the food name
        TextView name = (TextView)findViewById(R.id.name);
        name.setText(food.getName());

        //Populate the food description
        TextView description = (TextView)findViewById(R.id.description);
        description.setText(food.getDescription());*/

        int reastaurantNo = (Integer)getIntent().getExtras().get(EXTRA_RESTAURANTNO);
        SQLiteDatabase db = null;
        Cursor restaurantCursor = null;
        try {
            SQLiteOpenHelper starbuzzDatabaseHelper = new StarbuzzDatabaseHelper(this);
            db = starbuzzDatabaseHelper.getReadableDatabase();

            String sql = "SELECT * FROM RESTAURANT r, RESTAURANT_CATEGORY rc " +
                    "WHERE r._id = rc.RESTAURANT_ID AND rc._id = " + Integer.toString(reastaurantNo);
            //Log.e("myApp", sql);

            restaurantCursor = db.rawQuery(sql, null);

            if (restaurantCursor.moveToFirst()) {

                TextView name = (TextView)findViewById(R.id.name);
                name.setText(restaurantCursor.getString(restaurantCursor.getColumnIndex("NAME")));

                TextView url = (TextView)findViewById(R.id.url);
                url.setText(restaurantCursor.getString(restaurantCursor.getColumnIndex("URL")));

                TextView phone = (TextView)findViewById(R.id.phone);
                phone.setText(restaurantCursor.getString(restaurantCursor.getColumnIndex("PHONE")));

                TextView delivery_time = (TextView)findViewById(R.id.delivery_time);
                delivery_time.setText(restaurantCursor.getString(restaurantCursor.getColumnIndex("DELIVERY_TIME")));

                TextView free_delivery_from = (TextView)findViewById(R.id.free_delivery_from);
                free_delivery_from.setText(restaurantCursor.getString(restaurantCursor.getColumnIndex("FREE_DELIVERY_FROM")));

                TextView free_delivery_with_card = (TextView)findViewById(R.id.free_delivery_with_card);
                free_delivery_with_card.setText(restaurantCursor.getString(restaurantCursor.getColumnIndex("FREE_DELIVERY_WITH_CARD")));

                TextView card_pay = (TextView)findViewById(R.id.card_pay);
                card_pay.setText(restaurantCursor.getString(restaurantCursor.getColumnIndex("CARD_PAY")));

                TextView logo_url = (TextView)findViewById(R.id.logo_url);
                logo_url.setText(restaurantCursor.getString(restaurantCursor.getColumnIndex("LOGO_URL")));

                TextView rating = (TextView)findViewById(R.id.rating);
                rating.setText(restaurantCursor.getString(restaurantCursor.getColumnIndex("RATING")));
            }

        } catch (SQLiteException e) {
            Toast toast = Toast.makeText(this, "Database unavailable4", Toast.LENGTH_SHORT);
            toast.show();
            closeDb(restaurantCursor, db);
        }

        closeDb(restaurantCursor, db);

        /*
        name
        url
        phone
        delivery_time
        free_delivery_time
        free_delivery_with_card
        card_pay
        logo_url
        rating
        */

    }

    public void closeDb(Cursor categoriesCursor, SQLiteDatabase db) {
        if (categoriesCursor != null)
            categoriesCursor.close();
        if (db != null)
            db.close();
    }
}
