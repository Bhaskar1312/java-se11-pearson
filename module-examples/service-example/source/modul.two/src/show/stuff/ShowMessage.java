package show.stuff;

import srv.ServiceIF;

import java.util.ServiceLoader;

public class ShowMessage {
    public static void main(String[] args) {
        ServiceLoader<ServiceIF> sl = ServiceLoader.load(ServiceIF.class);
        System.out.println(sl);
        for(ServiceIF sif: sl) {
            System.out.println(sif.getClass()+ " says "+sif.message("it's a lovely"));
        }
        System.out.println("--- all services invoked ---");
    }
}
