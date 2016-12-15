package database;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.icu.text.SimpleDateFormat;
import android.icu.util.Calendar;
import android.icu.util.TimeZone;

@SuppressLint("NewApi")
@TargetApi(24)
public class Log {
    private int id;
    private String date;
    private String name;
    private String status;

    public Log(int id, String date, String name, String status) {
        this.id = id;
        this.date = date;
        this.name = name;
        this.status = status;
    }


    public Log(String name, boolean status) {
        setDate();
        setName(name);
        setStatus(status);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDate() {
        return date;
    }

    @TargetApi(24)
    @SuppressLint("NewApi")
    public void setDate() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd, HH:mm");
        sdf.setTimeZone(TimeZone.getTimeZone("GMT+1"));
        this.date = sdf.format(Calendar.getInstance().getTime());

    }

    public String getStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status ? "ON" : "OFF";
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String toStringSMS() {
        return getDate() + " " + getName() + " " + getStatus();
    }

    @Override
    public String toString() {
        return getId() + ") " + getDate() + " " + getName() + " " + getStatus();
    }


}
