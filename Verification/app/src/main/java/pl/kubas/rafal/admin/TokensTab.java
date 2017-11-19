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

import pl.kubas.rafal.database.DatabaseAdapter;


public class TokensTab extends AdminTab implements TabListener {
    private final int tabID = 2;
    private ActionBar adminBar;
    private CardView changeTokenStatusButton;
    private CardView generateNewSet;
    private ListView listTokens;
    private ArrayAdapter adapter;
    private DatabaseAdapter db;
    private Runnable run;
    private int selectedItemIndex = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tab_tokens);
        super.setActionBar(adminBar, this, tabID);
        setDatabase();
        setChangeTokenStatusButton();
        setGenerateNewSetButton();
        setTokenList();
        setTokenListUpdater();
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

    void setTokenListUpdater() {
        run = new Runnable() {
            @SuppressLint("NewApi")
            public void run() {
                adapter.clear();
                adapter.addAll(db.getAllTokensToString());
                adapter.notifyDataSetChanged();
                listTokens.invalidateViews();
                listTokens.refreshDrawableState();
            }
        };
    }

    private void updateDeviceList() {
        runOnUiThread(run);
    }


    private void setChangeTokenStatusButton() {
        changeTokenStatusButton = (CardView) findViewById(R.id.buttonChangeToken);
        changeTokenStatusButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    db.updateToken(Integer.valueOf(getItemIndex()));
                    updateDeviceList();
                    Toast.makeText(getBaseContext(), "Token status changed", Toast.LENGTH_LONG).show();
                } catch (Exception e) {
                }
            }
        });

    }

    private int getItemIndex() {
        String tokenIndex = adapter.getItem(selectedItemIndex).toString();
        tokenIndex = tokenIndex.substring((tokenIndex.indexOf("(") + 1), tokenIndex.indexOf(")"));
        return Integer.parseInt(tokenIndex);
    }

    private void setGenerateNewSetButton() {
        changeTokenStatusButton = (CardView) findViewById(R.id.buttonGenerateNewSet);
        changeTokenStatusButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    db.clearTokenTable();
                    db.assertTokenTableNotEmpty();
                    updateDeviceList();
                    Toast.makeText(getBaseContext(), "New token set generated", Toast.LENGTH_LONG).show();
                } catch (Exception e) {
                }
            }
        });
    }

    public void setTokenList() {
        adapter = new ArrayAdapter<String>(this, R.layout.list_text, db.getAllTokensToString());
        listTokens = (ListView) findViewById(R.id.listView);
        listTokens.setAdapter(adapter);
        listTokens.setSelector(android.R.color.darker_gray);

        listTokens.setOnItemClickListener(new OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                selectedItemIndex = position;
            }
        });
        {
        }

    }


}
