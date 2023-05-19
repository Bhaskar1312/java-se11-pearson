package lesson32;


import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.security.AccessController;
import java.security.PrivilegedAction;

public class Quiz {
    public static void main(String[] args)  {
        PrivilegedAction<String> pas = () -> {
            try(BufferedReader br = Files.newBufferedReader(Path.of("safefile.txt"));) {
                return br.readLine();
            } catch (IOException ex) {
                return "Failed";
            }
        };
        // line n1
        AccessController.doPrivileged(pas);

    }
}
