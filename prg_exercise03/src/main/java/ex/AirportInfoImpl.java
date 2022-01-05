package ex;

import ex.deserialization.objects.Flight;
import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Encoders;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SparkSession;
import scala.Tuple2;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import static org.apache.spark.sql.functions.*;

public class AirportInfoImpl implements AirportInfo {

    /**
     * Usage of some example operations.
     *
     * @param flights dataframe of flights
     */
    public void sparkExample(Dataset<Row> flights) {
        System.out.println("Example printSchema");
        flights.printSchema();

        System.out.println("Example select and filter");
        // operations on a dataframe or rdd do not modify it, but return a new one
        Dataset<Row> selectAirlineDisplayCode = flights.select("flight.operatingAirline.iataCode", "flight.aircraftType.icaoCode").filter(r -> !r.anyNull());
        selectAirlineDisplayCode.show(false);

        System.out.println("Example groupBy and count");
        Dataset<Row> countOfAirlines = selectAirlineDisplayCode.groupBy("iataCode").count();
        countOfAirlines.show(false);

        System.out.println("Example where");
        Dataset<Row> selectOnlyLufthansa = selectAirlineDisplayCode.where("iataCode = 'LH'");
        selectOnlyLufthansa.show(false);

        System.out.println("Example map to String");
        Dataset<String> onlyAircraftIcaoCodeAsString = selectOnlyLufthansa.map(r -> r.getString(1), Encoders.STRING());
        onlyAircraftIcaoCodeAsString.show(false);

        System.out.println("Example mapToPair and reduceByKey");
        JavaRDD<Row> rdd = selectOnlyLufthansa.toJavaRDD();
        System.out.println(rdd.first().toString());
        JavaPairRDD<String, Long> paired = rdd.mapToPair(r -> Tuple2.apply(r.getString(1), 1L));
        System.out.println("Visual inspection of paired : ");
        paired.take(20).forEach(t-> System.out.println(t._1()+" "+t._2()));
        JavaPairRDD<String, Long> reducedByKey = paired.reduceByKey((a, b) -> a + b);
        System.out.println("Visual inspection of reducedByKey : ");
        reducedByKey.take(20).forEach(t -> System.out.println(t._1() + " " + t._2()));
    }

    /**
     * Task 1
     * Return a dataframe, in which every row contains the destination airport and its count over all available data
     * of departing flights, sorted by count in descending order.
     * The column names are (in this order):
     * arrivalAirport | count
     * Remove entries that do not contain an arrivalAirport member (null or empty).
     * Use the given Dataframe.
     *
     * @param departingFlights Dataframe containing the rows of departing flights
     * @return a dataframe containing statistics of the most common destinations
     */
    @Override
    public Dataset<Row> mostCommonDestinations(Dataset<Row> departingFlights) {

        // creat a sparkSession
        SparkConf sparkConf=new SparkConf().setAppName("SparkExample").setMaster("local");
        SparkSession session=SparkSession.builder().config(sparkConf).getOrCreate();

        departingFlights.printSchema();
        Dataset<Row> datasetSelAirport=departingFlights.select("flight.arrivalAirport").filter(r->!r.anyNull());
        Dataset<Row> datasetGBAirport=datasetSelAirport.groupBy("arrivalAirport").count();
        Dataset<Row> datasetOBDec=datasetGBAirport.orderBy( col("count").desc());
        datasetOBDec.show();
        return datasetOBDec;

    }

    /**
     * Task 2
     * Return a dataframe, in which every row contains a gate and the amount at which that gate was used for flights to
     * Berlin ("count"), sorted by count in descending order. Do not include gates with 0 flights to Berlin.
     * The column names are (in this order):
     * gate | count
     * Remove entries that do not contain a gate member (null or empty).
     * Use the given Dataframe.
     *
     * @param departureFlights Dataframe containing the rows of departing flights
     * @return dataframe with statistics about flights to Berlin per gate
     */
    @Override
    public Dataset<Row> gatesWithFlightsToBerlin(Dataset<Row> departureFlights) {
        // In 2018, there are 2 airports which in operation in Berlin, one is Berlin Tegel Airport "Otto Lilienthal"
        // which abbreviated TXL, another is SXF aka Berlin Schönefeld Airport.
        Dataset<Row> dataset=departureFlights.where("flight.arrivalAirport='TXL' or flight.arrivalAirport='SXF'")
                .select("flight.departure.gates.gate")
                .where("gate IS NOT NULL and size(gate)>0");
        Dataset<Row> result=dataset.groupBy("gate").count().orderBy(col("count").desc()).where("count > 0");

        // convert ArrayType to String
        result=result.withColumn("gate",concat_ws(",",col("gate")));
        result.show();
        return result;
    }

    /**
     * Task 3
     * Return a JavaPairRDD with String keys and Long values, containing count of flights per aircraft on the given
     * originDate. The String keys are the modelNames of each aircraft and their Long value is the amount of flights for
     * that modelName at the given originDate. Do not include aircrafts with 0 flights.
     * Remove entries that do not contain a modelName member (null or empty).
     * The date string is of the form 'YYYY-MM-DD'.
     * Use the given dataframe.
     *
     * @param flights    Dataframe containing the rows of flights
     * @param originDate the date to find the most used aircraft for
     * @return tuple containing the modelName of the aircraft and its total number
     */
    @Override
    public JavaPairRDD<String, Long> aircraftCountOnDate(Dataset<Row> flights, String originDate) {

        flights.printSchema();
        String sql="flight.originDate = '" +originDate+"'";
        Dataset<Row> selectModelName=flights.where(sql).select("flight.aircraftType.modelName").filter(r->!r.anyNull());
        selectModelName.show(false);

        JavaRDD<Row> rdd = selectModelName.toJavaRDD();
        JavaPairRDD<String, Long> paired = rdd.mapToPair(r -> Tuple2.apply(r.getString(0), 1L));
        JavaPairRDD<String, Long> reducedByKey = paired.reduceByKey((a, b) -> a + b);
        reducedByKey.take(20).forEach(t -> System.out.println(t._1() + " " + t._2()));


        return reducedByKey;

    }
    ArrayList<String> datelist;
    /**
     * Task 4
     * Returns the date string of the day at which Ryanair had a strike in the given Dataframe.
     * The returned string is of the form 'YYYY-MM-DD'.
     * Hint: There were strikes at two days in the given period of time. Both are accepted as valid results.
     *
     * @param flights Dataframe containing the rows of flights
     * @return day of strike
     */
    @Override
    public String ryanairStrike(Dataset<Row> flights) {
        String start="2018-08-08";
        String end="2018-09-12";
        datelist=new ArrayList<>();
        flights.printSchema();

        Dataset<Row> dataset=flights.where("flight.operatingAirline.name='Ryanair'").select("flight.departure.actual")
                .filter(r->!r.anyNull()).orderBy(col("actual"));
        JavaRDD<Row> rdd = dataset.toJavaRDD();
        JavaPairRDD<String, Long> paired = rdd.mapToPair(r -> Tuple2.apply(r.getString(0), 1L));
        paired.take((int)paired.count()).forEach(t-> datelist.add(t._1()));

        for (int i = 0; i < datelist.size(); i++) {
            String strdate=datelist.get(i);
            datelist.set(i,strdate.substring(0,strdate.indexOf("T")));
        }
        datelist=removeDuplicates(datelist);

        datelist.forEach(r-> System.out.println(r));

        ArrayList<String> result=new ArrayList<>();
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date datestart=sdf.parse(start);
            Date date=null;
            for (int i = 0; i < datelist.size(); i++) {
                if (i==0){
                    date=datestart;
                }
                Date tmp=sdf.parse(datelist.get(i));
                if (!isSameDay(tmp,date)){
                    result.add(sdf.format(date));
                    i-=1;
                }
                Calendar c = Calendar.getInstance();
                c.setTime(date);
                c.add(Calendar.DATE, 1);  // number of days to add
                date = sdf.parse(sdf.format(c.getTime()));  // dt is now the new date


            }
        } catch (ParseException e) {
            e.printStackTrace();
        }

        System.out.println("result is : "+result.get(0));
        System.out.println("We have "+result.size()+" results");

        return result.get(0);
    }

    private static boolean isSameDay(Date date1, Date date2) {
        Calendar calendar1 = Calendar.getInstance();
        calendar1.setTime(date1);
        Calendar calendar2 = Calendar.getInstance();
        calendar2.setTime(date2);
        return calendar1.get(Calendar.YEAR) == calendar2.get(Calendar.YEAR)
                && calendar1.get(Calendar.MONTH) == calendar2.get(Calendar.MONTH)
                && calendar1.get(Calendar.DAY_OF_MONTH) == calendar2.get(Calendar.DAY_OF_MONTH);
    }

    // Function to remove duplicates from an ArrayList
    private static <T> ArrayList<T> removeDuplicates(ArrayList<T> list)
    {

        // Create a new LinkedHashSet
        Set<T> set = new LinkedHashSet<>();

        // Add the elements to set
        set.addAll(list);

        // Clear the list
        list.clear();

        // add the elements of set
        // with no duplicates to the list
        list.addAll(set);

        // return the list
        return list;
    }

    /**
     * Task 5
     * Returns a dataset of Flight objects. The dataset only contains flights of the given airline with at least one
     * of the given status codes. Uses the given Dataset of Flights.
     *
     * @param flights            Dataset containing the Flight objects
     * @param airlineDisplayCode the display code of the airline
     * @param status1            the status code of the flight
     * @param status             more status codes
     * @return dataset of Flight objects matching the required fields
     */
    @Override
    public Dataset<Flight> flightsOfAirlineWithStatus(Dataset<Flight> flights, String airlineDisplayCode, String status1, String... status) {
        // TODO: Implement
        return null;
    }

    /**
     * Task 6
     * Returns the average number of flights per day between the given timestamps (both included).
     * The timestamps are of the form 'hh:mm:ss'. Uses the given Dataset of Flights.
     * Hint: You only need to consider "scheduledTime" and "originDate" for this. Do not include flights with
     * empty "scheduledTime" or "originDate" fields. You can assume that lowerLimit is always before or equal
     * to upperLimit.
     *
     * @param flights Dataset containing the arriving Flight objects
     * @param lowerLimit     start timestamp (included)
     * @param upperLimit     end timestamp (included)
     * @return average number of flights between the given timestamps (both included)
     */
    @Override
    public double avgNumberOfFlightsInWindow(Dataset<Flight> flights, String lowerLimit, String upperLimit) {
        // TODO: Implement
        return 0.0d;
    }

    /**
     * Returns true if the first timestamp is before or equal to the second timestamp. Both timestamps must match
     * the format 'hh:mm:ss'.
     * You can use this for Task 6. Alternatively use LocalTime or similar API (or your own custom method).
     *
     * @param before the first timestamp
     * @param after  the second timestamp
     * @return true if the first timestamp is before or equal to the second timestamp
     */
    private static boolean isBefore(String before, String after) {
        for (int i = 0; i < before.length(); i++) {
            char bef = before.charAt(i);
            char aft = after.charAt(i);
            if (bef == ':') continue;
            if (bef < aft) return true;
            if (bef > aft) return false;
        }

        return true;
    }
}
