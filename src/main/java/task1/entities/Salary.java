package task1.entities;

import java.io.Serializable;

public class Salary implements Serializable {
    private transient double count;

    public Salary() {
    }

    public Salary(double count) {
        this.count = count;
    }

    public double getCount() {
        return count;
    }

    public void setCount(double count) {
        this.count = count;
    }

    @Override
    public String toString() {
        return "Salary{" +
                "count=" + String.format("%.2f", count) +
                '}';
    }
}
