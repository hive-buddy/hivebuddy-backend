package be.kdg.team9.hivebuddy.domain;

public class BeeHive {
    private int id;
    private int hiveNumber;

    public BeeHive(int id, int hiveNumber) {
        this.id = id;
        this.hiveNumber = hiveNumber;
    }

    public int getId() {
        return id;
    }

    public int getHiveNumber() {
        return hiveNumber;
    }
}
