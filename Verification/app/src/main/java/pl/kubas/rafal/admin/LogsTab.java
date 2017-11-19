package pl.kubas.rafal.admin;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBar.Tab;
import android.support.v7.app.ActionBar.TabListener;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.app.verification.R;

import java.util.List;

import pl.kubas.rafal.database.DatabaseAdapter;
import pl.kubas.rafal.database.Log;
import pl.kubas.rafal.manager.ManagerSMS;

public class LogsTab extends AdminTab implements TabListener {
    private final int tabID = 3;
    private ActionBar adminBar;
    private DatabaseAdapter db;
    private ArrayAdapter adapter;
    private ListView logList;
    private List<Log> list;
    private CardView clearLogsButton;
    private CardView deleteLogButton;
    private CardView sendLogSMSButton;
    private Runnable runUpdater;
    private ManagerSMS managerSMS;
    private int selectedItemIndex = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tab_logs);
        super.setActionBar(adminBar, this, tabID);
        setDatabase();
        setClearLogsButton();
        setSendLogSMSButton();
        setLogList();
        setLogListUpdater();
        managerSMS = new ManagerSMS(db);
    }

    @Override
    public void onTabReselected(Tab tab, FragmentTransaction ft) {
    }

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

    void setLogListUpdater() {
        runUpdater = new Runnable() {
            @SuppressLint("NewApi")
            public void run() {
                adapter.clear();
                adapter.addAll(db.getAllLogs());
                adapter.notifyDataSetChanged();
                logList.invalidateViews();
                logList.refreshDrawableState();
            }
        };
    }

    private void updateLogList() {
        runOnUiThread(runUpdater);
    }

    public void setClearLogsButton() {
        clearLogsButton = (CardView) findViewById(R.id.buttonClearLogsTable);
        clearLogsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                db.clearLogsTable();
                updateLogList();
                selectedItemIndex = -1;
                Toast.makeText(getBaseContext(), "Log list cleaned", Toast.LENGTH_LONG).show();
            }
        });
    }

    private void setSendLogSMSButton() {
        clearLogsButton = (CardView) findViewById(R.id.buttonSendLogSMS);
        clearLogsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isAnyItemSelected()) {
                    managerSMS.setMSG(adapter.getItem(selectedItemIndex).toString());
                    try {
                        managerSMS.sendSMS();
                    } catch (Exception e) {
                        Toast.makeText(getBaseContext(), "Problem with SMS sending", Toast.LENGTH_LONG).show();
                    }
                } else {
                    Toast.makeText(getBaseContext(), "Log not selected", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    private boolean isAnyItemSelected() {
        return selectedItemIndex == -1 ? false : true;
    }

    public void setLogList() {
        adapter = new ArrayAdapter<Log>(this, R.layout.list_text, db.getAllLogs());
        logList = (ListView) findViewById(R.id.logList);
        logList.setAdapter(adapter);
        logList.setSelector(android.R.color.darker_gray);

        logList.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                selectedItemIndex = position;
            }
        });
    }
}
