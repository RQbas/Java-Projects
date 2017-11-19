package pl.kubas.rafal.activities;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.app.verification.R;

import java.util.ArrayList;
import java.util.List;

import pl.kubas.rafal.admin.PhoneTab;
import pl.kubas.rafal.database.DatabaseAdapter;
import pl.kubas.rafal.database.PhoneNumber;


@SuppressLint("NewApi")
public class SettingsActivity extends AppCompatActivity {
    private DatabaseAdapter db;
    private TextInputEditText phoneTextField;
    private CardView buttonSave;
    private List<PhoneNumber> numbers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        setDatabase();
        setPhoneTextField();
        setSaveButton();
        setBackButton();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                startActivity(new Intent(SettingsActivity.this, MainActivity.class));
                finish();
                return true;
            case R.id.action_settings:
                startActivity(new Intent(SettingsActivity.this, PhoneTab.class));
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void setBackButton() {
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.logs, menu);
        return true;
    }


    @Override
    public void onBackPressed() {
        finish();
    }

    private void setDatabase() {
        db = new DatabaseAdapter(getApplicationContext());
        db.open();
    }


    public void setPhoneTextField() {
        phoneTextField = (TextInputEditText) findViewById(R.id.phoneNumberText);
        phoneTextField.setGravity(Gravity.CENTER_HORIZONTAL);
    }

    public void setSaveButton() {
        buttonSave = (CardView) findViewById(R.id.buttonSaveNumber);
        buttonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String phoneNumber = phoneTextField.getText().toString();
                if (!phoneNumber.equals("")) {
                    numbers = new ArrayList<>();
                    db.insertNumber(phoneNumber);
                    Toast.makeText(getBaseContext(), "Number added", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(getBaseContext(), "Number cannot be empty6", Toast.LENGTH_LONG).show();
                }

            }
        });
    }

}
