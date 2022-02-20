import java.sql.Array;
import java.util.*;

public class Main {

    static final Scanner scanner = new Scanner(System.in);
    // Тут создаем всех и говорим мэнеджеру ра
    public static void main(String[] args) throws InterruptedException {
        ArrayList<Integer> buffer = new ArrayList<>();
        Consumer consumer = new Consumer(buffer);
        System.out.println("Введите максимальное количество чисел в буффере:");
        int maxLength = scanner.nextInt();
        Producer producer = new Producer(buffer, maxLength);
        Manager manager = new Manager(consumer, producer, buffer);
        manager.DoWork();
    }
}
