package admin;

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
import database.DatabaseAdapter;
import database.PhoneNumber;


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
        phoneTextView = (TextView) findViewById(R.id.phoneTextView);
        phoneTextView.setText("Phone numbers panel");
    }

    public void setClearPhoneButton() {
        clearPhoneButton = (Button) findViewById(R.id.buttonClearPhoneTable);
        clearPhoneButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                db.clearPhoneTable();
            }
        });
    }


    public void setDeleteButton() {
        deleteButton = (Button) findViewById(R.id.buttonDeleteNumber);
        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String numberID = phoneTextView.getText().toString();
                try {
                    numberID = numberID.substring((numberID.indexOf("(") + 1), numberID.indexOf(")"));
                    db.deleteNumber(Integer.parseInt(numberID));
                } catch (Exception e) {
                }

            }
        });
    }


    public void setNumberList() {
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, db.getAllNumbers());
        listNumbers = (ListView) findViewById(R.id.listView);
        listNumbers.setAdapter(adapter);


        listNumbers.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String number = ((TextView) view).getText().toString();
                phoneTextView.setText(number);
            }
        });
        {
        }

    }



}
