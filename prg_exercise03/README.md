

## Answers of tasks
### 1.Statistics of the number of departing flights for each destination

```java
> Output:
+--------------+-----+
|arrivalAirport|count|
+--------------+-----+
|           TXL| 1809|
|           LHR| 1303|
|           HAM| 1132|
|           MUC| 1077|
|           CDG|  877|
|           VIE|  873|
|           AMS|  797|
|           PMI|  792|
|           MAD|  704|
|           ZRH|  696|
|           IST|  655|
|           BCN|  630|
|           BRU|  627|
|           LIS|  610|
|           FCO|  569|
|           ZWS|  556|
|           LIN|  545|
|           LEJ|  543|
|           DUS|  540|
|           GVA|  539|
+--------------+-----+
only showing top 20 rows

```

### 2. Number of flights to Berlin for each gate
```java
> Output: 
+----+-----+
|gate|count|
+----+-----+
|  C1|  230|
| A11|  207|
| A13|  161|
| A17|  149|
| A21|  122|
| A14|  115|
| A16|  108|
| A18|  101|
|  A1|   97|
| A15|   83|
|  B9|   75|
| A24|   55|
| A23|   44|
| A19|   39|
| A20|   27|
| A25|   21|
| A22|   20|
| A26|   19|
|  B1|   18|
|  B3|   18|
+----+-----+
only showing top 20 rows

```
   
### 3.Number of flights for each aircraft on date X

```java
> Output:
A306 2
F2TH 1
B772 8
TRN 3
AT72 1
DH8D 4
A35K 1
E75S 3
A319 74
A333 14
A346 7
A388 13
B77L 10
C56X 1
E135 1
A359 1
BUS 6
CRJ9 43
A32B 9
MD83 1

```

### 4.On which day did Ryanair have a strike contained in the data set
```java
> Output:
2018-08-10 
```

### 5.Flights of airline X with the flightstatus Y ( return to stand)
```java

> Dataset<Flight> flightsresult=uut.flightsOfAirlineWithStatus(flightDataset,"DE","S","X");

> flightsresult.show()

+------------------+--------------+----------------+------------+----------+--------------------+
|airlineDisplayCode|arrivalAirport|departureAirport|flightStatus|originDate|           scheduled|
+------------------+--------------+----------------+------------+----------+--------------------+
|                DE|           HHN|             FRA|           S|2018-08-29|2018-08-29T17:00:00Z|
|                DE|           HHN|             FRA|           X|2018-08-22|2018-08-22T15:00:00Z|
|                DE|           HHN|             FRA|           S|2018-08-15|2018-08-15T13:05:00Z|
|                DE|           MUC|             FRA|           S|2018-08-26|2018-08-26T03:00:00Z|
|                DE|           HAJ|             FRA|           X|2018-08-26|2018-08-26T13:00:00Z|
|                DE|           MUC|             FRA|           S|2018-09-01|2018-09-01T08:00:00Z|
|                DE|           STR|             FRA|           S|2018-08-24|2018-08-24T08:00:00Z|
|                DE|           DUS|             FRA|           S|2018-08-17|2018-08-17T13:00:00Z|
|                DE|           MUC|             FRA|           S|2018-08-26|2018-08-26T03:00:00Z|
|                DE|           HAJ|             FRA|           X|2018-08-26|2018-08-26T13:00:00Z|
|                DE|           LAS|             FRA|           X|2018-08-19|2018-08-19T09:15:00Z|
|                DE|           LAS|             FRA|           X|2018-08-19|2018-08-19T09:15:00Z|
|                DE|           DUS|             FRA|           S|2018-08-12|2018-08-12T04:00:00Z|
|                DE|           HAJ|             FRA|           S|2018-08-12|2018-08-12T16:10:00Z|
|                DE|           DUS|             FRA|           X|2018-08-12|2018-08-12T04:00:00Z|
|                DE|           FRA|             HHN|           S|2018-09-12|2018-09-12T03:10:00Z|
|                DE|           FRA|             PMI|           X|2018-08-08|2018-08-07T20:25:00Z|
|                DE|           FRA|             HAJ|           S|2018-09-03|2018-09-03T20:00:00Z|
|                DE|           FRA|             AYT|           X|2018-08-09|2018-08-09T13:40:00Z|
|                DE|           FRA|             MUC|           S|2018-08-09|2018-08-09T19:30:00Z|
+------------------+--------------+----------------+------------+----------+--------------------+
only showing top 20 rows

```

### 6.Average number of flights between the timestamps X and Y

```java

>flightDataset=fput.parseFlights(FLIGHTS_PATH);
String lowerlimit="12:00:00";
String upperlimit="18:30:00";
result=uut.avgNumberOfFlightsInWindow(flightDataset,lowerlimit,upperlimit);

> Output:
There are 42649 selected flights in the period of 37 days
the average number of flights per day is: 1152
```