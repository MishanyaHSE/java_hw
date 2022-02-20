import java.util.ArrayList;
import java.util.List;

public class Consumer extends Thread{
    final List<Integer> buffer;

    Consumer(ArrayList<Integer> buffer) {
        this.buffer = buffer;
    }

    @Override
    public void run() {
        while(!isInterrupted()) {
            synchronized (buffer) {
                try {
                    if (buffer.size() > 0) {
                        int output = buffer.get(0);
                        buffer.remove(0);
                        System.out.println("Потребитель забрал число " + output + " из буффера!");
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
