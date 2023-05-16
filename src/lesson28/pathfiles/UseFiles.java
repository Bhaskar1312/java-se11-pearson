package lesson28.pathfiles;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.attribute.BasicFileAttributes;
import java.nio.file.attribute.DosFileAttributes;
import java.nio.file.attribute.PosixFileAttributes;
import java.nio.file.attribute.PosixFilePermission;
import java.nio.file.attribute.PosixFilePermissions;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class UseFiles {
    public static void main(String[] args) throws Throwable{
        var attr = Files.readAttributes(Path.of("."), BasicFileAttributes.class);
        // var attr = Files.readAttributes(Path.of(".", "paths", ".TestMe.txt"), PosixFileAttributes.class); // not supported in windows
        // var attr = Files.readAttributes(Path.of(".", "paths", ".TestMe.txt"), DosFileAttributes.class);

        System.out.println(attr);
        System.out.println(attr.isDirectory());
        System.out.println(attr.creationTime());
        // System.out.println(attr.isHidden()); // only on DosFileAttributes

        System.out.println("----Attributes read from Files.readAttributes/dos:*----"); // posix?
        var attr2 = Files.readAttributes(Path.of("./paths/.TestMe.txt"), "dos:*");
        attr2.entrySet().forEach(
            e -> System.out.println(e.getKey() + " : " + e.getValue()
            // + "obj type is " + e.getValue().getClass()
            )
        );

        /**
        System.out.println("Attributes read from Files.getAttribute/posix:permissions");
        var attr3 = Files.getAttribute(Path.of("./paths/.TestMe.txt"), "posix:permissions");
        System.out.println("attr is a "+attr3.getClass());
        if(attr3 instanceof Set) {
            List<?>  hs = new ArrayList<>((Set) attr3);
            hs.forEach(x -> {
                System.out.println(x);
            });
            System.out.println("Element type: " + hs.get(0).getClass());
        }

         Set<PosixFilePermission> spfp = PosixFilePermissions.fromString("rw-r--r--");
         Files.setPosixFilePermissions(Path.of("./paths/.TestMe.txt"), spfp);

         */


    }
}
