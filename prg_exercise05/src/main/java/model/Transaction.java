package model;

import java.time.LocalDateTime;

public class Transaction {
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
    public String toString() {
        return "Transaction{" +
                "source='" + source + '\'' +
                ", destination='" + destination + '\'' +
                ", airplanesamount=" + airplanesamount +
                ", time=" + time +
                '}';
    }
}
