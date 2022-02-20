import java.util.ArrayList;
import java.util.List;

public class Manager {
    List<Integer> buffer;
    Consumer consumer;
    Producer producer;

    Manager(Consumer consumer, Producer producer, ArrayList<Integer> buffer) {
        this.buffer = buffer;
        this.consumer = consumer;
        this.producer = producer;
    }

    void DoWork() throws InterruptedException {
        consumer.start();
        producer.start();
        Thread.sleep(2000);
        consumer.interrupt();
        producer.interrupt();
        consumer.join();
        producer.join();
    }
}
