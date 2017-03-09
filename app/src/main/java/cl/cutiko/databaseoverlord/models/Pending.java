package cl.cutiko.databaseoverlord.models;

/**
 * Created by cutiko on 09-03-17.
 */

public class Pending {

    private int id;
    private String name;
    private boolean status;

    public Pending() {
    }

    public Pending(String name, boolean status) {
        this.name = name;
        this.status = status;
    }

    public Pending(int id, String name, boolean status) {
        this.id = id;
        this.name = name;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean getStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
}
