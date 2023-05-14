package lesson24;

public class RaceCondition {
    public static void main(String[] args) {
        final int[] data = {0};
        new Thread(()->{
            while (data[0] < 4){
                System.out.println("> "+data[0]);
            }
        }).start();

        new Thread(()->{
          for(int i=0;i<5;i++) {
              data[0] = i;
          }
        }).start();
    }
    // can print nothing, can print only one number, or a few
}
