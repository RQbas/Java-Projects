package admin;

import com.app.verification.R;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBar.Tab;
import android.support.v7.app.ActionBar.TabListener;
import database.DatabaseAdapter;


public class TokensTab extends AdminTab implements TabListener {
    final int tabID = 2;
    ActionBar adminBar;
    DatabaseAdapter db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tab_tokens);
        super.setActionBar(adminBar, this, tabID);
        setDatabase();
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
}
