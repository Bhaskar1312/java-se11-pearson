package lesson30;

import java.util.Locale;
import java.util.PropertyResourceBundle;
import java.util.ResourceBundle;

public class useResourceBundle {
    public static void main(String[] args) {
        // Locale l = Locale.US; // will look for file_en_US, then file_en, them file (note underscore)
        // Locale l = Locale.UK;

        Locale l = Locale.CANADA_FRENCH; // no _fr_CA, ->no fr -> default

        System.out.println(Locale.getDefault());
        Locale.setDefault(l);

        System.out.println("For locale: "+ l + "--------------");
        ResourceBundle bundle = PropertyResourceBundle.getBundle("localization.MyResources"); // put where classloader is, as a package or put it in a directory marked as resources(delete compiled classes folder)
        // in localization, put individual files

        System.out.println("car-engine-cover: "+ bundle.getString("car-engine-cover"));
        System.out.println("cake: "+bundle.getString("cake"));
        System.out.println("baked-item: "+ bundle.getString("baked-item"));
        System.out.println("affirmation: "+bundle.getString("affirmation"));

    }
}
