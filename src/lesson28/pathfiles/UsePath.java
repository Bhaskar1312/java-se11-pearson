package lesson28.pathfiles;

import java.io.IOException;
import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.nio.file.StandardWatchEventKinds;

public class UsePath {
    public static void main(String[] args) throws IOException, InterruptedException {
        Path abFile = Path.of("paths", "a", "b", "file-a-b.txt");
        Path cPath = Path.of("paths", "c");

        // extract paths of the file
        System.out.println(abFile.getFileName());
        // elements by index, iterator.
        System.out.println("element 2 is "+abFile.getName(2));
        abFile.iterator().forEachRemaining(System.out::println);
        // See also compareTo, startsWith, endsWith,
        // getParent, getRoot, subPath, toFile etc.
        // register for watching

        System.out.println("path is absolute? "+ abFile.isAbsolute());
        System.out.println("absolute path is "+ abFile.toAbsolutePath());

        Path odd = Path.of(".", "..", "java-se11-pearson", "paths", "a", "b", "file-a-b.txt");
        System.out.println("odd is "+ odd);
        System.out.println("normalized is "+ odd.normalize());

        System.out.println("resolve, joining paths "+cPath.resolve("d/file-c-d.txt"));
        System.out.println("to get from paths/c to file-a-b: "+ cPath.relativize(abFile));

        try(var ws = FileSystems.getDefault().newWatchService();) {
            Path pathsDir = Path.of("paths");
            var registrationKey = pathsDir.register(ws,
                StandardWatchEventKinds.ENTRY_CREATE,
                StandardWatchEventKinds.ENTRY_DELETE,
                StandardWatchEventKinds.ENTRY_MODIFY);
            while (true) {
                var key = ws.take();
                System.out.println("something happened");
                for(var event: key.pollEvents()) {
                    System.out.println("fileName "+event.context());
                }
                registrationKey.reset();
            }
        }
    }
}
