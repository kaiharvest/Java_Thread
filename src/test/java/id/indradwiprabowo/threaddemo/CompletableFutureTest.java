package id.indradwiprabowo.threaddemo;

import org.junit.jupiter.api.Test;

import java.util.Random;
import java.util.concurrent.*;


public class CompletableFutureTest {

    private ExecutorService executorService = Executors.newFixedThreadPool(10);

    private Random random = new Random();

    public Future<String> getValue() {
        CompletableFuture<String> future = new CompletableFuture<>();

        executorService.execute(() -> {
            try {
                Thread.sleep(2000);
                future.complete("Selesai");
            } catch (InterruptedException e) {
                future.completeExceptionally(e);
            }
        });

        return future;
    };

    @Test
    void create() throws ExecutionException, InterruptedException {
        Future<String> future = getValue();

        System.out.println(future.get());
    }

    private void execute(CompletableFuture<String> future, String value) {
        executorService.execute(() -> {
            try {
                Thread.sleep(1000 * random.nextInt(5000));
                future.complete(value);
            } catch (InterruptedException e) {
                future.completeExceptionally(e);
            }
        });
    }

    public Future<String> getFasttest() {
        CompletableFuture<String> future = new CompletableFuture<>();

        execute(future, "Thread 1");
        execute(future, "Thread 2");
        execute(future, "Thread 3");

        return future;
    }

    @Test
    void testFasttest() throws ExecutionException, InterruptedException {
        System.out.println(getFasttest().get());
    }

}
