package io.github.zhaomy6.lifebattery;

import android.content.Intent;
<<<<<<< HEAD
=======
import android.database.Cursor;
>>>>>>> cc84a0ebccce4fab7085d71bc48ac4cea2f3b5a0
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

<<<<<<< HEAD
public class MainActivity extends AppCompatActivity implements View.OnClickListener {
=======
public class MainActivity extends AppCompatActivity
    implements View.OnClickListener {
>>>>>>> cc84a0ebccce4fab7085d71bc48ac4cea2f3b5a0
    private MyDB myDB;
    private TextView title, DDL, progress;
    private Button planBotton, storeBotton, summaryButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

<<<<<<< HEAD
        findViewById(R.id.m_plan_button).setOnClickListener(this);
        findViewById(R.id.m_store_button).setOnClickListener(this);
        findViewById(R.id.m_summary_button).setOnClickListener(this);
//        storeBotton= (Button) findViewById(R.id.m_store_button);
//        summaryButton = (Button) findViewById(R.id.m_summary_button);
=======
        Button toPlans = (Button) findViewById(R.id.m_plan_button);
        toPlans.setOnClickListener(this);
        Button toStore = (Button) findViewById(R.id.m_store_button);
        toStore.setOnClickListener(this);
        Button toStatistic = (Button) findViewById(R.id.m_summary_button);
        toStatistic.setOnClickListener(this);
>>>>>>> cc84a0ebccce4fab7085d71bc48ac4cea2f3b5a0
//        myDB = new MyDB(this);
//        title = (TextView)findViewById(R.id.m_plan_title);
//        DDL = (TextView)findViewById(R.id.m_plan_ddl);
//        progress = (TextView)findViewById(R.id.m_plan_progress);
//
//        Cursor cursor = myDB.getAll();
//        String titleContent = "", DDLContent= "", progressContent = "";
//        while (cursor.moveToNext()) {
//            String tmp = cursor.getString(cursor.getColumnIndex("DDL"));
//            if (tmp.compareTo(DDLContent) > 0) {
//                titleContent = cursor.getString(cursor.getColumnIndex("title"));
//                DDLContent = tmp;
//                progressContent = cursor.getString(cursor.getColumnIndex("progress"));
//            }
//        }
//        title.setText(titleContent);
//        DDL.setText(DDLContent);
//        progress.setText(progressContent);
    }

    @Override
    public void onClick(View view) {
<<<<<<< HEAD
        Intent intent = new Intent();
        intent.setClass(MainActivity.this, MainActivity.class);
        switch (view.getId()) {
            case R.id.m_plan_button:
                intent.setClass(MainActivity.this, PlansActivity.class);
                break;
            case R.id.m_store_button:
                intent.setClass(MainActivity.this, PlansActivity.class);
                break;
            case R.id.m_summary_button:
                intent.setClass(MainActivity.this, StatisticsActivity.class);
                break;
            default:
                break;
        }
        startActivity(intent);
=======
        Intent intent = null;
        switch (view.getId()) {
            case R.id.m_store_button:
                intent = new Intent(MainActivity.this, StoreActivity.class);
                startActivity(intent);
                break;
            case R.id.m_summary_button:
                intent = new Intent(MainActivity.this, StatisticsActivity.class);
                startActivity(intent);
                break;
            case R.id.m_plan_button:
                intent = new Intent(MainActivity.this, PlansActivity.class);
                startActivity(intent);
                break;
            default:
                Toast.makeText(this, "点击逻辑未处理", Toast.LENGTH_SHORT).show();
        }
>>>>>>> cc84a0ebccce4fab7085d71bc48ac4cea2f3b5a0
    }
}
