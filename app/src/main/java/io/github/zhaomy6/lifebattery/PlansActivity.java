package io.github.zhaomy6.lifebattery;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.CursorAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;

import java.util.List;

public class PlansActivity extends AppCompatActivity {
    private ListView listView;
    private PlanAdapter planAdapter;
    private List<Plan> planList;
    private MyDB myDB;
    private SimpleCursorAdapter sca;

    private Toolbar.OnMenuItemClickListener onMenuItemClick = new Toolbar.OnMenuItemClickListener() {
        @Override
        public boolean onMenuItemClick(MenuItem menuItem) {
            if (menuItem.getItemId() == R.id.addAction) {
                Intent intent = new Intent();
                intent.setClass(PlansActivity.this, AddActivity.class);
                startActivity(intent);
                return true;
            }
            return false;
        }
    };

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    public void updateListView() {
        Cursor cursors = myDB.getPart();
        sca.swapCursor(cursors);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (resultCode) {
            case RESULT_OK:
                updateListView();
                break;
            default:
                break;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plans);

        Toolbar toolbar = (Toolbar)findViewById(R.id.toolBar);
        toolbar.setTitle("LifeBattery");
        setSupportActionBar(toolbar);
        toolbar.setOnMenuItemClickListener(onMenuItemClick);

        myDB = new MyDB(this);
        Cursor listItems = myDB.getPart();
        sca = new SimpleCursorAdapter(getApplicationContext(), R.layout.plans_item,
                listItems, new String[] {"title", "DDL"},
                new int[]{R.id.planTitle, R.id.planDDL}, CursorAdapter.FLAG_REGISTER_CONTENT_OBSERVER);
        listView = (ListView) findViewById(R.id.planList);
        listView.setAdapter(sca);


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                LayoutInflater factory = LayoutInflater.from(PlansActivity.this);
                View views = factory.inflate(R.layout.dialoglayout, null);
                AlertDialog.Builder builder = new AlertDialog.Builder(PlansActivity.this);
                builder.setView(views);

                TextView d_planTitle = (TextView)views.findViewById(R.id.d_planTitle);
                TextView d_planDDL = (TextView)views.findViewById(R.id.d_planDDL);
                TextView d_planTag = (TextView)views.findViewById(R.id.d_planTag);
                TextView d_planDetail = (TextView)views.findViewById(R.id.d_planDetail);

                Cursor cursor = (Cursor)sca.getItem(position);
                final String titleText = cursor.getString(cursor.getColumnIndex("title"));
                d_planTitle.setText(titleText);
                final String DDLText = cursor.getString(cursor.getColumnIndex("DDL"));
                d_planDDL.setText(DDLText);

                Cursor cursor1 = myDB.getWithTitle(titleText);
                cursor1.moveToFirst();

                final String progressText = cursor1.getString(cursor1.getColumnIndex("progress"));
                d_planTag.setText(progressText);
                final String detailText = cursor1.getString(cursor1.getColumnIndex("detail"));
                d_planDetail.setText(detailText);

                builder.setTitle(titleText);
                builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        //  do nothing
                    }
                });
                builder.setPositiveButton("完成任务", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        myDB.deleteDB(titleText);
                        updateListView();
                    }
                });

                d_planTitle.setText(titleText);
                d_planDDL.setText(DDLText);
                builder.create().show();
            }

        });

        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener(){
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, long id) {
                Cursor cursor = (Cursor)sca.getItem(position);
                final String title = cursor.getString(cursor.getColumnIndex("title"));
                AlertDialog.Builder builder = new AlertDialog.Builder(PlansActivity.this);
                builder.setTitle("是否删除");

                builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        myDB.deleteDB(title);
                        updateListView();
                    }
                });

                builder.setNegativeButton("取消", null);
                builder.create().show();
                return true;
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        updateListView();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        sca.swapCursor(null);
    }
}
