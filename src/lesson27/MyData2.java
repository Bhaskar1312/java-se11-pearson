package lesson27;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

// Quiz
class MoreData2 {
    MoreData2(int x) {}
    // Deserialization fails at readObject bcoz of lack of public constructor,
    // coz there is no info to pass up for x

}
public class MyData2 extends MoreData2 implements Serializable {
    private String name;
    private int count;

    public MyData2(String name, int count) {
        super(count);
        this.name = name;
        this.count = count;
    }

    @Override
    public String toString() {
        return "MyData2{" +
            "name='" + name + '\'' +
            ", count=" + count +
            '}';
    }

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        MyData2 md = new MyData2("Fred", 99);
        try (ObjectOutputStream oos =
                 new ObjectOutputStream(new FileOutputStream("data.ser"))
        ) {
            System.out.println("Writing object: "+ md);
            oos.writeObject(md);
            oos.flush();
        }

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("data.ser"))){
            Object read = ois.readObject();
            MyData2 md2 = (MyData2) read;
            System.out.println("Read object: "+ md2);
        }
    }
}
