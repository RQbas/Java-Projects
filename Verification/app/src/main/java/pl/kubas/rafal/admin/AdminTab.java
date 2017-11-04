package pl.kubas.rafal.admin;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBar.Tab;
import android.support.v7.app.ActionBar.TabListener;
import android.support.v7.app.ActionBarActivity;
import android.view.MenuItem;

import java.util.ArrayList;

import pl.kubas.rafal.activities.SettingsActivity;

@SuppressLint("NewApi")
public class AdminTab extends ActionBarActivity implements TabListener {

    private boolean firstInitialization = true;
    private Context tabActivityContext;
    ArrayList<Tab> tabs;
    String[] tabNames = {"Phone", "Devices", "Tokens", "Logs"};


    @Override
    public void onTabReselected(Tab tab, FragmentTransaction ft) {
    }

    @Override
    public void onTabSelected(Tab tab, FragmentTransaction ft) {
    }

    @Override
    public void onTabUnselected(Tab tab, FragmentTransaction ft) {
        updateFirstInitialization();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                Intent intent = new Intent(this, SettingsActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public boolean isFirstInitialization() {
        return firstInitialization;
    }

    public void updateFirstInitialization() {
        firstInitialization = false;
    }

    public ActionBar setActionBar(ActionBar ab, ActionBar.TabListener listener, int index) {
        ab = getSupportActionBar();
        ab.setHomeButtonEnabled(true);
        ab.setDisplayHomeAsUpEnabled(true);
        setTabs(listener);

        for (int i = 0; i < tabs.size(); i++) {
            if (i == index)
                ab.addTab(tabs.get(i), true);
            else
                ab.addTab(tabs.get(i));
        }

        ab.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
        return ab;
    }

    public void setTabs(ActionBar.TabListener listener) {
        tabs = new ArrayList<Tab>();
        for (int i = 0; i < tabNames.length; i++) {
            tabs.add(getSupportActionBar().newTab().setText(tabNames[i]).setTabListener(listener));
        }
    }

    public void setTabActivity(Context context, int index) {
        if (!isFirstInitialization()) {
            switch (index) {
                case 0:
                    startActivity(new Intent(context, PhoneTab.class));
                    break;
                case 1:
                    startActivity(new Intent(context, DevicesTab.class));
                    break;
                case 2:
                    startActivity(new Intent(context, TokensTab.class));
                    break;
                case 3:
                    startActivity(new Intent(context, LogsTab.class));
                    break;
                default:
                    startActivity(new Intent(context, SettingsActivity.class));
                    break;
            }
        }
    }


}
