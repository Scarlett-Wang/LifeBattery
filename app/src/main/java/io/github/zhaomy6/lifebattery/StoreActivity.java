package io.github.zhaomy6.lifebattery;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import java.util.ArrayList;

public class StoreActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_store);

        ArrayList<String> data = new ArrayList<>();
        data.add("活过本学期");
        data.add("周游世界");
        StoreAdapter adapter = new StoreAdapter(this, R.layout.store_item, data);
        ListView lv = (ListView) findViewById(R.id.store_list);
        lv.setAdapter(adapter);
    }
}
