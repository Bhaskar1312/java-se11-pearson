package lesson27;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.time.LocalDate;

class MoreData {
    @Override
    public String toString() {
        return "More Data";
    }
}

public class MyData
     implements Serializable
{
    private String name;
    private int count;
    private LocalDate date;

    private transient MoreData md = new MoreData();

    public MyData(String name, int count, LocalDate date) {
        System.out.println("Constructing MyData");
        this.name = name;
        this.count = count;
        this.date = date;
    }

    @Override
    public String toString() {
        return "MyData{" +
            "name='" + name + '\'' +
            ", count=" + count +
            ", date=" + date +
            ", md =" + md +
            '}';
    }

    public static void main(String[] args) throws Throwable{
        MyData md = new MyData("Fred", 99, LocalDate.now());
        try (ObjectOutputStream oos =
                new ObjectOutputStream(new FileOutputStream("data.ser"))
        ) {
            System.out.println("Writing object: "+ md);
            oos.writeObject(md);
            // the object has to be serializable, the fields have to be serializable or transient,
            // and non-serializable parent class must have default constructor
            // allocate object space and write null, zero, false over it
            oos.flush();
        }

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("data.ser"))){
            Object read = ois.readObject(); // the system doesn't what kind of object it is
            // deserialization doesn't call constructor
            MyData md2 = (MyData) read;
            System.out.println("Read object: "+ md2);
        }
    }
}
