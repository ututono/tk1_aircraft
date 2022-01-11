package ex.deserialization;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import ex.deserialization.objects.Flight;
import ex.deserialization.objects.FlightObj;
import org.apache.spark.SparkConf;
import org.apache.spark.api.java.function.MapPartitionsFunction;
import org.apache.spark.sql.*;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class FlightParserImpl implements FlightParser {

    private SparkSession sparkSession;

    public FlightParserImpl() {
        SparkConf conf = new SparkConf().setAppName("SparkExercise").setMaster("local");
        sparkSession = SparkSession.builder().config(conf).getOrCreate();
    }

    /**
     * Returns a Dataframe, containing all objects from the JSON files in the given path.
     *
     * @param path the path containing (only) the JSON files
     * @return Dataframe containing the input data
     */
    @Override
    public Dataset<Row> parseRows(String path) {
        return sparkSession.read().json(path);
    }

    /**
     * Deserializes the JSON lines to Flight objects. Each String in the given Dataset represents a JSON object.
     * Use Gson for this.
     * Hints: Apply a map function (mapPartitions) on the dataset, in which you use Gson to deserialize each line into objects of FlightObj.
     * Use GsonBuilder to register the type adapter "FlightAdapter" for FlightObj and to create a Gson object.
     *
     * @param path the path to parse the jsons from
     * @return dataset of Flight objects parsed from the JSON lines
     */
    @Override
    public Dataset<Flight> parseFlights(String path) {
        Dataset<String> lines = sparkSession.sqlContext().read().textFile(path);

        // create a GsonBuilder which force serialization of null value registering a typeAdapter
        Gson gs=new GsonBuilder().registerTypeAdapter(FlightObj.class,new FlightAdapter()).serializeNulls().create();

        FlightAdapter flightAdapter=new FlightAdapter();
//        Dataset<Flight> flightDataset = lines.mapPartitions((MapPartitionsFunction<String, Flight>) input -> {
//            ArrayList<Flight> flights=new ArrayList<>();
//            while (input.hasNext()) {
////                    FlightObj flightObj=gs.fromJson(input.next(),FlightObj.class);
////                    flights.add(flightObj.getFlight());
//                flights.add(new Flight());
//            }
//            return flights.iterator();
//        },Encoders.bean(Flight.class));

        List<String> jsonStringlist;
        ArrayList<Flight> flightsList=new ArrayList<>();
        jsonStringlist=lines.collectAsList();

        for (String jsonStr: jsonStringlist
             ) {
            FlightObj flightObj=gs.fromJson(jsonStr,FlightObj.class);
            flightsList.add(flightObj.getFlight());
        };
        SparkConf conf = new SparkConf().setAppName("SparkExercise").setMaster("local");
        sparkSession = SparkSession.builder().config(conf).getOrCreate();
        Dataset<Flight> flightDataset=sparkSession.createDataset(flightsList,Encoders.bean(Flight.class));
        flightDataset.printSchema();
        return flightDataset;
    }
}
