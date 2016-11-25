package devicelist;

public class Device {
    private long id;
    private String name;
    private boolean isOn;

    public Device(int id, String name) {
        this.setId(id);
        this.name = name;
        this.isOn = false;
    }

    public long getId() {
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

    void changeStatus() {
        this.isOn = !this.isOn;
    }


}
