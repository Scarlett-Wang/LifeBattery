package io.github.zhaomy6.lifebattery;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;

import java.util.LinkedList;
import java.util.List;

public class PlansActivity extends AppCompatActivity {
    private ListView listView;
    private PlanAdapter planAdapter;
    private List<Plan> planList;
    private MyDB myDB;
    private SimpleCursorAdapter sca;

    public void updateListView() {
        Cursor cursors = myDB.getAll();
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

        myDB = new MyDB(this);
        final List<Plan> listItems = new LinkedList<Plan>();
        listItems.add(new Plan("任务title", "DDL", "progress", ""));

        final PlanAdapter planAdapter = new PlanAdapter(this, listItems);
        final ListView listView = (ListView) findViewById(R.id.planList);
        listView.setAdapter(planAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                LayoutInflater factory = LayoutInflater.from(PlansActivity.this);
                View views = factory.inflate(R.layout.dialoglayout, null);
                AlertDialog.Builder builder = new AlertDialog.Builder(PlansActivity.this);
                builder.setView(views);

                TextView d_planTitle = (TextView)views.findViewById(R.id.d_planTitle);
                TextView d_planDDL = (TextView)views.findViewById(R.id.d_planDDL);
                TextView d_planProgress = (TextView)views.findViewById(R.id.d_planProgress);
                TextView d_planDetail = (TextView)views.findViewById(R.id.d_planDetail);
                Plan item = (Plan)planAdapter.getItem(position);
                final String titleText = item.getTitle();
                final String DDLText = item.getDDL();
                final String progressText = item.getCurrentProgress();
                final String detailText = item.getPlanDetail();

                builder.setTitle(titleText);
                builder.setNegativeButton("取消", null);
                builder.setPositiveButton("完成", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });

                d_planTitle.setText(titleText);
                d_planDDL.setText(DDLText);
                d_planProgress.setText(progressText);
                d_planDetail.setText(detailText);
                builder.create().show();
            }

        });

        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener(){

            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, long id) {
                AlertDialog.Builder builder = new AlertDialog.Builder(PlansActivity.this);
                builder.setTitle("是否删除");

                builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        planList.remove(position);
                        planAdapter.notifyDataSetChanged();
                    }
                });

                builder.setNegativeButton("取消", null);
                builder.create().show();
                return true;
            }
        });

        Button add = (Button) findViewById(R.id.add);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setClass(PlansActivity.this, AddActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        sca.swapCursor(null);
    }
}
