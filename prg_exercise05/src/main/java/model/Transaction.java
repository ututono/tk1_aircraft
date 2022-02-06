package model;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;

public class Transaction implements Serializable {
    private String source;
    private String destination;
    private Integer airplanesamount;
    private LocalDateTime time;

    public Transaction() {
    }

    public Transaction(String source, String destination, Integer airplanesamount, LocalDateTime time) {
        this.source = source;
        this.destination = destination;
        this.airplanesamount = airplanesamount;
        this.time = time;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Transaction that = (Transaction) o;
        return Objects.equals(source, that.source) && Objects.equals(destination, that.destination) && Objects.equals(airplanesamount, that.airplanesamount) && Objects.equals(time, that.time);
    }

    @Override
    public int hashCode() {
        return Objects.hash(source, destination, airplanesamount, time);
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "source='" + source + '\'' +
                ", destination='" + destination + '\'' +
                ", airplanesamount=" + airplanesamount +
                ", time=" + time +
                '}';
    }
}
