package lesson30;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAccessor;

public class FormParse {
    public static void main(String[] args) {
        LocalDate ld = LocalDate.of(1996, 1, 23);
        DateTimeFormatter dtf1 = DateTimeFormatter.ISO_LOCAL_DATE;
        System.out.println(dtf1.format(ld));

        DateTimeFormatter dtf2 = DateTimeFormatter.ofPattern("dd MMMM yyyy"); // for input/output - d date M - month y - year
        System.out.println(dtf2.format(ld));
        // dd - always 2 digits even if single digit date, d - 1 character width for single digit date
        // MMMM - moth in long hand

        String date = "25 September 2018";
        TemporalAccessor d = dtf2.parse(date); // d is not local date
        System.out.println(dtf1.format(d));
    }
}
