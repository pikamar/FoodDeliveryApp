package fooddelivery.fooddelivery;

import android.app.ListActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.view.View;
import android.content.Intent;

public class FoodCategoryActivity extends ListActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ListView listFood = getListView();
        ArrayAdapter<Food> listAdapter = new ArrayAdapter<Food>(this,
                                                                  android.R.layout.simple_list_item_1,
                                                                  Food.FOODs);
        listFood.setAdapter(listAdapter);
    }

    @Override
    public void onListItemClick(ListView listView,
                                View itemView,
                                int position,
                                long id) {
        Intent intent = new Intent(FoodCategoryActivity.this, FoodActivity.class);
        intent.putExtra(FoodActivity.EXTRA_FOODNO, (int) id);
        startActivity(intent);
    }
}
