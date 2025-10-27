package id.indradwiprabowo.threaddemo;

import org.junit.jupiter.api.Test;

public class ThreadCommunicationTest {

    private String message = null;

    @Test
    void thread() throws InterruptedException {
        var thread1 = new Thread(() -> {
            while (message == null) {
                // wait
            }
            System.out.println(message);
        });

        var thread2 = new Thread(() -> {
            message = "Indra Dwi Prabowo";
        });

        thread2.start();
        thread1.start();

        thread2.join();
        thread1.join();
    }
}
