package ex;

import ex.deserialization.FlightParser;
import ex.deserialization.FlightParserImpl;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.junit.jupiter.api.*;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class YourTests {

    static final String DEPARTURES = "./Fraport/*DEPARTURES*";
    static final String FLIGHTS_PATH = "./Fraport/2018-0*";
    static AirportInfo uut;
    static FlightParser fput;
    static Dataset<Row> departureRows;
    static Dataset<Row> flights;
    @BeforeAll
    static void init() {
        Logger.getLogger("org").setLevel(Level.ERROR);
        Logger.getLogger("akka").setLevel(Level.ERROR);
        uut = new AirportInfoImpl();
        fput = new FlightParserImpl();
        departureRows = fput.parseRows(DEPARTURES);
        flights=fput.parseRows(FLIGHTS_PATH);

    }

    Dataset<Row> result;


    @Test
    @DisplayName("Number of columns")
    void checkNumberOfColumns() {
        uut.sparkExample(flights);
        assertEquals(2, 2);
    }

    @Nested
    @DisplayName("Task 4")
    @TestInstance(TestInstance.Lifecycle.PER_CLASS)
    class RyanairStrikeTest {

        String result;

        @BeforeAll
        @Timeout(30)
        void setup() {
            result=uut.ryanairStrike(flights);
        }

        @Test
        @DisplayName("Check format of return")
        void formatTest() {
            System.out.println(result);
            Assertions.assertTrue(true);
        }

    }

    @Nested
    @DisplayName("Task 6")
    @TestInstance(TestInstance.Lifecycle.PER_CLASS)
    class MyTest23 {

        String result;


        @Test
        @DisplayName("Check format of return")
        void formatTest() {
            String strdate="2018-08-08T05:02:46Z";
            String newstr=strdate.substring(0,strdate.indexOf("T"));
            System.out.println(strdate.indexOf("T"));
            System.out.println(newstr);
        }

    }
}
