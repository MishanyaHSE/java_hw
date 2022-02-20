import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Producer extends Thread {

    final List<Integer> buffer;
    int maxLength;
    Random rnd = new Random();
    Producer(ArrayList<Integer> buffer, int maxLength) {
        this.buffer = buffer;
        this.maxLength = maxLength;
    }
    @Override
    public void run() {
        while(!isInterrupted()) {
            synchronized (buffer) {
                try {
                    if (buffer.size() < maxLength) {
                        int nextNumber = rnd.nextInt(10);
                        buffer.add(nextNumber);
                        System.out.println("Производитель добавил число " + nextNumber +  " в буффер!");
                        buffer.notifyAll();
                        Thread.sleep(10);
                    } else {
                        buffer.wait();
                    }
                } catch (InterruptedException e) {
                    interrupt();
                }
            }
        }
    }
}
