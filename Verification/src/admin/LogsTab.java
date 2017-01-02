package admin;

import java.util.List;

import com.app.verification.R;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBar.Tab;
import android.support.v7.app.ActionBar.TabListener;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import database.DatabaseAdapter;
import database.Log;


public class LogsTab extends AdminTab implements TabListener {
    final int tabID = 3;
    ActionBar adminBar;
    DatabaseAdapter db;
    ArrayAdapter adapter;
    TextView logTextView;
    ListView logList;
    List<Log> list;
    Button clearLogsButton;
    Button deleteLogButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tab_logs);
        super.setActionBar(adminBar, this, tabID);
        setDatabase();
        setTextView();
        setClearLogsButton();
        setLogList();
    }

    @Override
    public void onTabReselected(Tab tab, FragmentTransaction ft) {}

    @Override
    public void onTabSelected(Tab tab, FragmentTransaction ft) {
        super.setTabActivity(this, tab.getPosition());
    }

    @Override
    public void onTabUnselected(Tab arg0, FragmentTransaction arg1) {
        super.updateFirstInitialization();

    }

    private void setDatabase() {
        db = new DatabaseAdapter(getApplicationContext());
        db.open();
    }

    public void setTextView() {
        logTextView = (TextView) findViewById(R.id.logTextView);
        logTextView.setText("Logs");
    }

    public void setClearLogsButton() {
        clearLogsButton = (Button) findViewById(R.id.buttonClearLogsTable);
        clearLogsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                db.clearLogsTable();
                Toast.makeText(getBaseContext(), "Log list cleaned", Toast.LENGTH_LONG).show();
            }
        });
    }

    public void setLogList() {
        adapter = new ArrayAdapter<Log>(this, android.R.layout.simple_list_item_1, db.getAllLogs());
        logList = (ListView) findViewById(R.id.logList);
        logList.setAdapter(adapter);
        logList.setOnItemClickListener(new OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String device = ((TextView) view).getText().toString();
                logTextView.setText(device);
                Toast.makeText(getBaseContext(), "Log seleted", Toast.LENGTH_LONG).show();
            }
        });
        {
        }
    }
}
