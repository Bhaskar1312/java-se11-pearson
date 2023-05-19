package lesson31;

public class Quiz {
    public static void collectResults(String countRequested) {
        Double limit = Double.parseDouble(countRequested);
        if(limit > 10) throw new IllegalArgumentException("too many rows reqested(in sql)");
        int rows = 0;
        while (true) {
            if(++rows > limit) break; // ++rows > NaN fails infact ++rows < NaN or == also fails

            System.out.println("row ="+ rows+" limit="+limit);

        }
    }
    public static void main(String[] args) {

        // Take input from user
        collectResults("NaN"); // Denial of service in sql
    }
}
