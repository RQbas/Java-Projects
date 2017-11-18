package pl.kubas.rafal.lists;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListAdapter;
import android.widget.TextView;

import com.app.verification.R;

import pl.kubas.rafal.database.DatabaseAdapter;
import pl.kubas.rafal.database.Log;

public class LogListAdapter extends BaseAdapter implements ListAdapter {
    private TextView logTextView;
    private CardView logCardView;
    private DatabaseAdapter db;
    private Context context;


    public LogListAdapter(Context context, DatabaseAdapter db) {
        setDatabaseAdapter(db);
        setContext(context);
    }


    private void setDatabaseAdapter(DatabaseAdapter db) {
        this.db = db;
    }

    private void setContext(Context context) {
        this.context = context;
    }

    @Override
    public int getCount() {
        return db.getAllLogs().size();
    }

    @Override
    public Object getItem(int position) {
        return db.getAllLogs().get(position);
    }

    @Override
    public long getItemId(int position) {
        return db.getAllLogs().get(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        if (view == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.list_log, null);
        }
        logCardView = (CardView) view.findViewById(R.id.log_cardview);
        logTextView = (TextView) view.findViewById(R.id.log_text);
        logTextView.setText(db.getAllLogs().get(position).toString());


        return view;
    }

}


