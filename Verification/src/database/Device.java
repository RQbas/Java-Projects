package database;

public class Device {
    private int id;
    private String name;
    private boolean isOn;

    public Device(int id, String name, boolean isOn) {
        this.id = id;
        this.name = name;
        this.isOn = isOn;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isOn() {
        return isOn;
    }

    public void setOn(boolean isOn) {
        this.isOn = isOn;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String displayForButton() {
        if (!isOn)
            return "Activate";
        else
            return "Deactivate";

    }

    public void changeStatus() {
        this.isOn = !this.isOn;
    }

    @Override
    public String toString() {
        return "(" + getId() + ") " + getName() + " " + String.valueOf(isOn());

    }


}
