package fooddelivery.fooddelivery;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

class StarbuzzDatabaseHelper extends SQLiteOpenHelper {

    private static final String DB_NAME = "fooddelivery"; // the name of our database
    private static final int DB_VERSION = 1; // the version of the database

    StarbuzzDatabaseHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    private static void insertCategory(SQLiteDatabase db, int id, String name, String pictureUrl) {
        ContentValues categryValues = new ContentValues();
        categryValues.put("_id", id);
        categryValues.put("NAME", name);
        categryValues.put("PICTUREURL", pictureUrl);
        db.insert("CATEGORY", null, categryValues);
    }

    private static void insertRestaurant(SQLiteDatabase db, int id, String name, String url, String phone, String delivery_time
            , String free_delivery_from, String free_delivery_with_card, String card_pay, String logo_url, String rating) {
        ContentValues restaurantValues = new ContentValues();
        restaurantValues.put("_id", id);
        restaurantValues.put("NAME", name);
        restaurantValues.put("URL", url);
        restaurantValues.put("PHONE", phone);
        restaurantValues.put("DELIVERY_TIME", delivery_time);
        restaurantValues.put("FREE_DELIVERY_FROM", free_delivery_from);
        restaurantValues.put("FREE_DELIVERY_WITH_CARD", free_delivery_with_card);
        restaurantValues.put("CARD_PAY", card_pay);
        restaurantValues.put("LOGO_URL", logo_url);
        restaurantValues.put("RATING", rating);
        db.insert("RESTAURANT", null, restaurantValues);
    }

    private static void insertRestaurantCategory(SQLiteDatabase db, int id, int restaurant_id, int category_id, String menu_url) {
        ContentValues categryValues = new ContentValues();
        categryValues.put("_id", id);
        categryValues.put("RESTAURANT_ID", restaurant_id);
        categryValues.put("CATEGORY_ID", category_id);
        categryValues.put("MENU_URL", menu_url);
        db.insert("RESTAURANT_CATEGORY", null, categryValues);
    }

    private static void insertComment(SQLiteDatabase db, int id, String content, String user, int restaurant_id) {
        ContentValues categryValues = new ContentValues();
        categryValues.put("_id", id);
        categryValues.put("CONTENT", content);
        categryValues.put("USER", user);
        categryValues.put("RESTAURANT_ID", restaurant_id);
        db.insert("COMMENT", null, categryValues);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        updateMyDatabase(db, 0, DB_VERSION);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        updateMyDatabase(db, oldVersion, newVersion);
    }

    private void updateMyDatabase(SQLiteDatabase db, int oldVersion, int newVersion) {
        if (oldVersion < 1) {
            db.execSQL("CREATE TABLE CATEGORY (_id INTEGER, "
                    + "STATUS TEXT, "
                    + "CREATED_AT TEXT, "
                    + "UPDATED_AT TEXT, "
                    + "NAME TEXT, "
                    + "PICTUREURL TEXT);");

            insertCategory(db, 1, "Pizza", "Pizza.png");
            insertCategory(db, 2, "Sushi", "Sushi.png");
            insertCategory(db, 3, "Kebabs and burgers", "Kebabs_and_burgers.png");
            insertCategory(db, 4, "Barbecue", "Barbecue.png");
            insertCategory(db, 5, "WOK", "WOK.png");
            insertCategory(db, 6, "Pasta", "Pasta.png");
            insertCategory(db, 7, "Snacks", "Snacks.png");
            insertCategory(db, 8, "Soup", "Soup.png");
            insertCategory(db, 9, "Main course", "Main_course.png");
            insertCategory(db, 10, "Salad", "Salad.png");
            insertCategory(db, 11, "Desserts", "Desserts.png");
            insertCategory(db, 12, "Discounts", "Discounts.png");

            db.execSQL("CREATE TABLE RESTAURANT (_id INTEGER, "
                    + "STATUS TEXT, "
                    + "CREATED_AT TEXT, "
                    + "UPDATED_AT TEXT, "
                    + "NAME TEXT, "
                    + "URL TEXT, "
                    + "PHONE TEXT, "
                    + "DELIVERY_TIME TEXT, "
                    + "FREE_DELIVERY_FROM TEXT, "
                    + "FREE_DELIVERY_WITH_CARD TEXT, "
                    + "CARD_PAY TEXT, "
                    + "LOGO_URL TEXT, "
                    + "RATING TEXT);");

            insertRestaurant(db, 1, "Lido", "www.lido.lv", "223462", "30 min", "25", "no", "yes", "lodo.png", "5");
            insertRestaurant(db, 2, "Chili", "www.chili.lv", "223462", "30 min", "25", "no", "yes", "lodo.png", "5");
            insertRestaurant(db, 3, "Erebuni", "www.erebuni.lv", "223462", "30 min", "25", "no", "yes", "lodo.png", "5");
            insertRestaurant(db, 4, "Elande", "www.elande.lv", "223462", "30 min", "25", "no", "yes", "lodo.png", "5");
            insertRestaurant(db, 5, "Kebabs", "www.kebabs.lv", "223462", "30 min", "25", "no", "yes", "lodo.png", "5");

            db.execSQL("CREATE TABLE RESTAURANT_CATEGORY (_id INTEGER, "
                    + "STATUS TEXT, "
                    + "CREATED_AT TEXT, "
                    + "UPDATED_AT TEXT, "
                    + "RESTAURANT_ID INTEGER, "
                    + "CATEGORY_ID INTEGER, "
                    + "MENU_URL TEXT);");

            insertRestaurantCategory(db, 1, 1, 1, "www.menu.lv");
            insertRestaurantCategory(db, 2, 1, 2, "www.menu.lv");
            insertRestaurantCategory(db, 3, 1, 3, "www.menu.lv");
            insertRestaurantCategory(db, 4, 2, 4, "www.menu.lv");
            insertRestaurantCategory(db, 5, 2, 5, "www.menu.lv");
            insertRestaurantCategory(db, 6, 2, 6, "www.menu.lv");
            insertRestaurantCategory(db, 7, 3, 7, "www.menu.lv");
            insertRestaurantCategory(db, 8, 3, 8, "www.menu.lv");
            insertRestaurantCategory(db, 9, 3, 9, "www.menu.lv");
            insertRestaurantCategory(db, 10, 4, 10, "www.menu.lv");
            insertRestaurantCategory(db, 11, 4, 11, "www.menu.lv");
            insertRestaurantCategory(db, 12, 4, 12, "www.menu.lv");
            insertRestaurantCategory(db, 13, 5, 3, "www.menu.lv");
            insertRestaurantCategory(db, 14, 5, 4, "www.menu.lv");
            insertRestaurantCategory(db, 15, 5, 5, "www.menu.lv");

            db.execSQL("CREATE TABLE COMMENT (_id INTEGER, "
                    + "STATUS TEXT, "
                    + "CREATED_AT TEXT, "
                    + "UPDATED_AT TEXT, "
                    + "CONTENT TEXT, "
                    + "USER TEXT, "
                    + "RESTAURANT_ID INTEGER);");

            insertComment(db, 1, "comment1", "Joe", 1);
            insertComment(db, 2, "comment2", "Joe", 2);
            insertComment(db, 3, "comment3", "Joe", 3);
            insertComment(db, 4, "comment4", "Joe", 4);
            insertComment(db, 5, "comment5", "Joe", 5);
            insertComment(db, 6, "comment6", "Joe", 6);
        }
    }
}