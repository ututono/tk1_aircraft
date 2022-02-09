package model;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

public class Transaction implements Serializable {
    private String source;
    private String destination;
    private Integer airplanesamount;
    private List<String> airplanes;
    private LocalDateTime time;


    public Transaction(String source, String destination, List<String> airplanes, LocalDateTime time) {
        this.source = source;
        this.destination = destination;
        this.airplanes=airplanes;
        this.airplanesamount = airplanes.size();
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

    public String getSource() {
        return source;
    }

    public String getDestination() {
        return destination;
    }

    public Integer getAirplanesamount() {
        return airplanesamount;
    }

    public LocalDateTime getTime() {
        return time;
    }

    public List<String> getAirplanes() {
        return airplanes;
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
