package ex;

import java.time.LocalDateTime;

public class MyTest {

    public static void main(String[] args) {
        String strdate="2018-08-08T05:02:46Z";
        LocalDateTime dateTime= LocalDateTime.parse(strdate);
        System.out.println(dateTime);
    }
}
