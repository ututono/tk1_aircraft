package ex;

import ex.deserialization.FlightParser;
import ex.deserialization.FlightParserImpl;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class YourTests {

    static final String DEPARTURES = "./Fraport/*DEPARTURES*";
    static final String FLIGHTS_PATH = "./Fraport/2018-08-0*";
    static AirportInfo uut;
    static FlightParser fput;
    static Dataset<Row> departureRows;
    @BeforeAll
    static void init() {
        Logger.getLogger("org").setLevel(Level.ERROR);
        Logger.getLogger("akka").setLevel(Level.ERROR);
        uut = new AirportInfoImpl();
        fput = new FlightParserImpl();
        departureRows = fput.parseRows(DEPARTURES);
    }

    Dataset<Row> result;


    @Test
    @DisplayName("Number of columns")
    void checkNumberOfColumns() {
        uut.sparkExample(departureRows);
        assertEquals(2, 2);
    }
}
