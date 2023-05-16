package lesson28.pathfiles;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class Recursion {
    public static void main(String[] args) throws IOException {
        System.out.println("-----------");
        /**
         * walk(Path start, int maxDepth, FileVisitOption... options)
         * */
        Files.walk(Path.of("src") /*, 2*/)
            .forEach(p-> System.out.println(p
                // .normalize().toAbsolutePath()
                )
            );
        System.out.println("------------");
        /**
         * find(Path start, int maxDepth, BiPredicate<Path, BasicFileAttributes> matcher, FileVisitor... options)
         */
        Files.find(Path.of("."), 2,
            (p, a)-> true /*a.isDirectory()*/)
            .forEach(System.out::println);
    }
}
