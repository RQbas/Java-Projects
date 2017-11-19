package pl.kubas.rafal.activities;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.ListView;

import com.app.verification.R;

import java.util.List;

import pl.kubas.rafal.database.DatabaseAdapter;
import pl.kubas.rafal.database.Log;
import pl.kubas.rafal.lists.LogListAdapter;


@SuppressLint("NewApi")
public class LogsActivity extends AppCompatActivity {
    private DatabaseAdapter db;
    private LogListAdapter adapter;
    private ListView logList;
    private List<Log> list;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logs);
        setDatabase();
        setLogList();
        setBackButton();
    }

    @Override
    public void onBackPressed() {
        finish();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void setBackButton() {
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    private void setDatabase() {
        db = new DatabaseAdapter(getApplicationContext());
        db.open();
    }


    public void setLogList() {
        adapter = new LogListAdapter(this, db);
        logList = (ListView) findViewById(R.id.logList);
        logList.setAdapter(adapter);

    }

}
