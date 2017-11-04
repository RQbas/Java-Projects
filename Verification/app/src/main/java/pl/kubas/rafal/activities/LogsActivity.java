package pl.kubas.rafal.activities;

import java.util.List;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.app.verification.R;

import pl.kubas.rafal.database.DatabaseAdapter;
import pl.kubas.rafal.database.Log;


@SuppressLint("NewApi")
public class LogsActivity extends ActionBarActivity {
    DatabaseAdapter db;
    ArrayAdapter adapter;
    TextView logTextView;
    ListView logList;
    List<Log> list;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logs);
        setDatabase();
        setTextView();
        setLogList();
    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(LogsActivity.this, MainActivity.class));
    }

    private void setDatabase() {
        db = new DatabaseAdapter(getApplicationContext());
        db.open();
    }

    public void setTextView() {
        logTextView = (TextView) findViewById(R.id.logTextView);
        logTextView.setText("Logs");
    }

    public void setLogList() {
        adapter = new ArrayAdapter<Log>(this, android.R.layout.simple_list_item_1, db.getAllLogs());
        logList = (ListView) findViewById(R.id.logList);
        logList.setAdapter(adapter);

    }
}
