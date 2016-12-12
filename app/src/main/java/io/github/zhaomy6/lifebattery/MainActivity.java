package io.github.zhaomy6.lifebattery;

import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private MyDB myDB;
    private TextView title, DDL, progress;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        myDB = new MyDB(this);
//
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
}
