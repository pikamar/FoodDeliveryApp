package fooddelivery.fooddelivery;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class FoodActivity extends Activity {

    public static final String EXTRA_FOODNO = "foodNo";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food);
        
        //Get the food from the intent
        int foodNo = (Integer)getIntent().getExtras().get(EXTRA_FOODNO);
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
        description.setText(food.getDescription());
    }
}
