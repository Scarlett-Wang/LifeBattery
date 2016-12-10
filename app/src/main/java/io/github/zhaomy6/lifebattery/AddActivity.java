package io.github.zhaomy6.lifebattery;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AddActivity extends AppCompatActivity {
    private EditText title;
    private  EditText DDL;
    private EditText progress;
    private EditText detail;
    private Button button;
    private  MyDB myDB = new MyDB(this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        title = (EditText)findViewById(R.id.titleEdit);
        DDL = (EditText)findViewById(R.id.DDLEdit);
        progress = (EditText)findViewById(R.id.progressEdit);
        detail = (EditText)findViewById(R.id.detailEdit);
        button = (Button)findViewById(R.id.addButton);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String titleText = title.getText().toString();
                String DDLText = DDL.getText().toString();
                String progressText = progress.getText().toString();
                String detailText = detail.getText().toString();


                if (titleText.equals("")) {
                    Toast.makeText(AddActivity.this, "名字为空,请完善", Toast.LENGTH_SHORT).show();
                } else {
                    if (myDB.isExists(titleText)) {
                        Toast.makeText(AddActivity.this, "此任务已经存在", Toast.LENGTH_SHORT).show();
                    } else {
                        myDB.insert2DB(titleText, DDLText, progressText, detailText);
                        Intent intent = new Intent();
                        intent.setClass(AddActivity.this, PlansActivity.class);
                        startActivity(intent);
                        finish();
                    }
                }
            }
        });
    }
}
