package fooddelivery.fooddelivery;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private Button btnUpdate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnUpdate = (Button)findViewById(R.id.btnUpdate);
    }

    public void btnUpdateClicked(View view) {
        new DBJSONFetchTask().execute();
    }
}
