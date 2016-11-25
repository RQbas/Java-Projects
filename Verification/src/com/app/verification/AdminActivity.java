package com.app.verification;

import java.util.List;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
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
public class AdminActivity extends ActionBarActivity {
    DatabaseAdapter db;
    Button deleteButton;
    Button clearButton;
    TextView adminTextView;
    ListView listNumbers;
    List<PhoneNumber> numbers;
    ArrayAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);
        setDatabase();
        setTextView();
        setDeleteButton();
        setClearButton();
        setNumberList();

    }


    private void setDatabase() {
        db = new DatabaseAdapter(getApplicationContext());
        db.open();
    }

    public void setTextView() {
        adminTextView = (TextView) findViewById(R.id.adminTextView);
        adminTextView.setText("Admin panel");
    }

    public void setClearButton() {
        clearButton = (Button) findViewById(R.id.buttonClearTable);
        clearButton.setOnClickListener(new View.OnClickListener() {
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
                String numberID = adminTextView.getText().toString();
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
                adminTextView.setText(number);
            }
        });
        {
        }

    }


}
