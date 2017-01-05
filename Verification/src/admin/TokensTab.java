package admin;

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


public class TokensTab extends AdminTab implements TabListener {
    final int tabID = 2;
    ActionBar adminBar;
    TextView tokenTextView;
    Button addButton;
    ListView listTokens;
    ArrayAdapter adapter;
    DatabaseAdapter db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tab_tokens);
        super.setActionBar(adminBar, this, tabID);
        setDatabase();
        setTextView();
        setAddButton();
        setTokenList();
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
        tokenTextView = (TextView) findViewById(R.id.tokenTextView);
        tokenTextView.setText("Tokens");
    }


    private void setAddButton() {
        addButton = (Button) findViewById(R.id.buttonAddToken);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    //
                    Toast.makeText(getBaseContext(), "Token added", Toast.LENGTH_LONG).show();
                } catch (Exception e) {
                }
            }
        });

    }

    public void setTokenList() {
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, db.getAllTokensToString());
        listTokens = (ListView) findViewById(R.id.listView);
        listTokens.setAdapter(adapter);


        listTokens.setOnItemClickListener(new OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String device = ((TextView) view).getText().toString();
                tokenTextView.setText(device);
            }
        });
        {
        }

    }


}
