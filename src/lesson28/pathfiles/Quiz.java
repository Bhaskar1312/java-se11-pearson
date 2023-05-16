package lesson28.pathfiles;

import java.io.FileInputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;

public class Quiz {
    public static void main(String[] args) throws Throwable{
        ByteBuffer bb = ByteBuffer.allocate(1024);
        try (FileChannel fcIn = new FileInputStream("paths/data.txt").getChannel();
             FileChannel fcOut = FileChannel.open(Path.of("paths","append.txt"),
                 StandardOpenOption.CREATE, StandardOpenOption.APPEND);){
            fcIn.read(bb); // position=46, limit=1024, capacity=1024
            // so if we dont flip before writing 1024-46=978  NUL \0 chars will be written
            // bb.flip(); // after flip, position = 0, limit=46
            fcOut.write(bb); // before this line, we are not calling bb.flip()
            // in the absence of flip(), the limit is set at the very end of the read buffer position,
            // 1 beyond the end of the buffer. And the position pointer is just after, the valid piece of data in there.
            // The remainder of the buffer is probably filled with zeros(1024 - read bytes, no of bytes in buffer)
        }
    }
}
