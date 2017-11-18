package pl.kubas.rafal.activities;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.app.verification.R;

import java.util.ArrayList;
import java.util.List;

import pl.kubas.rafal.admin.PhoneTab;
import pl.kubas.rafal.database.DatabaseAdapter;
import pl.kubas.rafal.database.PhoneNumber;


@SuppressLint("NewApi")
public class SettingsActivity extends AppCompatActivity {
    DatabaseAdapter db;

    TextView settingsTextView;
    TextInputEditText phoneTextField;
    CardView buttonSave;
    ListView listNumbers;
    String ADMIN_PASS = "+1+";
    private List<PhoneNumber> numbers;

    ArrayAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        setDatabase();
        setTextView();
        setPhoneTextField();
        setSaveButton();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.logs, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            String pass = phoneTextField.getText().toString();
            if (pass.equals("+1")) {
                Intent launchAdminPanel = new Intent(SettingsActivity.this, PhoneTab.class);
                startActivity(launchAdminPanel);
            }
            return true;
        }
        return false;
    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(SettingsActivity.this, MainActivity.class));
    }

    private void setDatabase() {
        db = new DatabaseAdapter(getApplicationContext());
        db.open();
    }


    public void setPhoneTextField() {
        phoneTextField = (TextInputEditText) findViewById(R.id.phoneNumberText);
        phoneTextField.setGravity(Gravity.CENTER_HORIZONTAL);
    }

    public void setTextView() {
        settingsTextView = (TextView) findViewById(R.id.settingsTextView);
        settingsTextView.setText("  Enter phone number  ");
    }

    public void setSaveButton() {
        buttonSave = (CardView) findViewById(R.id.buttonSaveNumber);
        buttonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String phoneNumber = phoneTextField.getText().toString();
                numbers = new ArrayList<>();
                db.insertNumber(phoneNumber);
                Toast.makeText(getBaseContext(), "Number added", Toast.LENGTH_LONG).show();

            }
        });
    }

}
