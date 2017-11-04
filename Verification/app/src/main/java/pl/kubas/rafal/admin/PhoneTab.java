package pl.kubas.rafal.admin;

import java.util.List;

import com.app.verification.R;

import android.annotation.SuppressLint;
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
import pl.kubas.rafal.database.DatabaseAdapter;
import pl.kubas.rafal.database.PhoneNumber;


@SuppressLint("NewApi")
public class PhoneTab extends AdminTab implements TabListener {
    final int tabID = 0;
    DatabaseAdapter db;
    Button deleteButton;
    Button clearPhoneButton;
    TextView phoneTextView;
    ListView listNumbers;
    List<PhoneNumber> numbers;
    ArrayAdapter adapter;
    ActionBar adminBar;
    Runnable runUpdater;
    int selectedItemIndex = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tab_phone);
        super.setActionBar(adminBar, this, tabID);
        setDatabase();
        setTextView();
        setDeleteButton();
        setClearPhoneButton();
        setNumberList();
        setPhoneListUpdater();
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

    void setPhoneListUpdater() {
        runUpdater = new Runnable() {
            @SuppressLint("NewApi")
            public void run() {
                adapter.clear();
                adapter.addAll(db.getAllNumbers());
                adapter.notifyDataSetChanged();
                listNumbers.invalidateViews();
                listNumbers.refreshDrawableState();
            }
        };
    }

    private void updateDeviceList() {
        runOnUiThread(runUpdater);
    }

    public void setTextView() {
        phoneTextView = (TextView) findViewById(R.id.phoneTextView);
        phoneTextView.setText("Phone numbers panel");
    }

    public void setClearPhoneButton() {
        clearPhoneButton = (Button) findViewById(R.id.buttonClearPhoneTable);
        clearPhoneButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                db.clearPhoneTable();
                updateDeviceList();
                Toast.makeText(getBaseContext(), "Numbers list cleaned", Toast.LENGTH_LONG).show();
            }
        });
    }


    public void setDeleteButton() {
        deleteButton = (Button) findViewById(R.id.buttonDeleteNumber);
        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    db.deleteNumber(getItemIndex());
                    updateDeviceList();
                    Toast.makeText(getBaseContext(), "Number deleted", Toast.LENGTH_LONG).show();
                } catch (Exception e) {
                }
            }
        });
    }

    private int getItemIndex() {
        String numberIndex = adapter.getItem(selectedItemIndex).toString();
        numberIndex = numberIndex.substring((numberIndex.indexOf("(") + 1), numberIndex.indexOf(")"));
        return Integer.parseInt(numberIndex);
    }


    public void setNumberList() {
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, db.getAllNumbers());
        listNumbers = (ListView) findViewById(R.id.listView);
        listNumbers.setAdapter(adapter);
        listNumbers.setSelector(android.R.color.darker_gray);

        listNumbers.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                selectedItemIndex = position;
            }
        });
        {
        }

    }



}
