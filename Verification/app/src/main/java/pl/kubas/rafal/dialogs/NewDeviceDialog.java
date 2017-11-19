package pl.kubas.rafal.dialogs;

import android.app.Activity;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.CardView;
import android.view.Gravity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.app.verification.R;

import pl.kubas.rafal.database.DatabaseAdapter;

/**
 * Created by rkubas on 19.11.17.
 */

public class NewDeviceDialog {
    private View dialogView;
    private CardView addDeviceButton;
    private AlertDialog alertDialog;
    private EditText deviceNameField;
    private Activity activity;
    private DatabaseAdapter db;


    public NewDeviceDialog(Activity activity, DatabaseAdapter db) {
        setActivityComponents(activity, db);
        createDialogView();
        setDialogComponents();
        createAlertDialog();
        setDialogComponentsListeners();

    }

    private void setDialogComponentsListeners() {
        addDeviceButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String deviceName = deviceNameField.getText().toString();
                try {
                    db.insertDevice(deviceName, false);
                    Toast.makeText(activity, "Device added", Toast.LENGTH_LONG).show();
                    deviceNameField.getText().clear();
                } catch (Exception e) {
                }
            }
        });

    }

    private void setActivityComponents(Activity activity, DatabaseAdapter db) {
        this.activity = activity;
        this.db = db;
    }

    private void createDialogView() {
        this.dialogView = activity.getLayoutInflater().inflate(R.layout.add_device_dialog, null);
    }

    private void setDialogComponents() {
        this.addDeviceButton = (CardView) dialogView.findViewById(R.id.buttonAddDevice);
        deviceNameField = (EditText) dialogView.findViewById(R.id.deviceNameField);
        deviceNameField.setGravity(Gravity.CENTER_HORIZONTAL);
    }

    private void createAlertDialog() {
        createAlertUsingBuilder();
    }

    private void createAlertUsingBuilder() {
        final AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        builder.setView(this.dialogView);
        alertDialog = builder.create();
        alertDialog.show();
    }


}
