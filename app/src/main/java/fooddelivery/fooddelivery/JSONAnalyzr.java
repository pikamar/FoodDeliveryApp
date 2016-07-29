package fooddelivery.fooddelivery;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class JSONAnalyzr {

    public static List<Category> getCategories(String input) {
        List<Category> categories = new ArrayList<>();
        try {
            JSONArray jsonArray = new JSONArray(input);
            JSONObject json;
            for (int i = 0; i < jsonArray.length(); i++) {
                json = jsonArray.getJSONObject(i);
                Category category = new Category();
                category.setName(json.getString("name"));
                category.setId(json.getLong("id"));
                System.out.println(category);
                categories.add(category);

            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return categories;
    }

    public static List<Restaurant> getRestaurants(String input) {
        List<Restaurant> restaurants = new ArrayList<>();
        try {
            JSONArray jsonArray = new JSONArray(input);
            JSONObject json;
            for (int i = 0; i < jsonArray.length(); i++) {
                json = jsonArray.getJSONObject(i);
                Restaurant restaurant = new Restaurant();
                restaurant.setName(json.getString("name"));
                restaurant.setId(json.getLong("id"));
                restaurant.setUrl(json.getString("url"));
                restaurant.setPhone(json.getString("phone"));
                restaurant.setDeliveryTime(json.getString("deliveryTime"));
                restaurant.setFreeDeliveryFrom(json.getString("freeDeliveryFrom"));
                restaurant.setFreeDeliveryWithCard(json.getString("freeDeliveryWithCard"));
                restaurant.setCardPay(json.getString("cardPay"));
                restaurant.setRating(json.getInt("rating"));
                System.out.println(restaurant);
                restaurants.add(restaurant);

            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return restaurants;
    }

    public static List<RestaurantCategory> getRestaurantCategories(String input) {
        List<RestaurantCategory> resCats = new ArrayList<>();
        try {
            JSONArray jsonArray = new JSONArray(input);
            JSONObject json;
            for (int i = 0; i < jsonArray.length(); i++) {
                json = jsonArray.getJSONObject(i);
                RestaurantCategory resCat = new RestaurantCategory();
                resCat.setId(json.getLong("id"));
                resCat.setMenuUrl(json.getString("menuURL"));
                resCat.setCategoryId(json.getLong("categoryId"));
                resCat.setRestaurantId(json.getLong("restaurantId"));
                System.out.println(resCat);
                resCats.add(resCat);

            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return resCats;
    }
}
