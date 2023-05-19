package lesson30;

import java.time.Month;
import java.util.Locale;

public class Printing {
    public static void main(String[] args) {
        // format specifier %[arg_idx$][flags][width][.precision]conversion // conversion e.g, floating point, calendar date

        // String template = "In the month of %1$tB, the %2$s meteor " +
        //     "shower can be seen.\n"+
        //     "If you are patient you can expect to see %3$3.1f shooting stars";
        // % - substitution, tB - month in text, $s - for textual representation, $3 - 3 digits - but won't throw away significant digits
        // %12.1f - padding so that 12 digits .1f - 1 digit after decimal  %1 -1st index, %2-2nd index

        // String template = "In the month of %tB, the %s meteor " +
        //     "shower can be seen.\n"+
        //     "If you are patient you can expect to see %3.1f shooting stars";

        String template = " the %2$s meteor In the month of %1$tB, " +
                "shower can be seen.\n"+
                "If you are patient you can expect to see %3$,12.1f shooting stars"; //$, depending on locale, different separators are used
        Month month = Month.APRIL;
        String showerName = "Perseid";
        System.out.printf(Locale.CANADA_FRENCH, template, month, showerName, 1_000.0); // consider template is 0th arg or format indexing starts at 1.

        // look at Formatter class

        System.out.printf("%n");
        long pan = 1;
        System.out.printf("%021d\n", pan);
        String ssn = "12";
        System.out.printf("%21s\n", ssn);

        // MessageFormat, NumberFormat, DateFormat, DateTimeFormatter

        System.out.printf("%2$5.2f", 1.2345, 9.8765, 4.6789); // " 9.88" ( 5 width)
    }
}
