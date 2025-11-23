package id.indradwiprabowo.threaddemo;

import org.junit.jupiter.api.Test;

import java.util.Random;
import java.util.concurrent.*;

public class CompletableFutureTest {

    private final ExecutorService executorService = Executors.newFixedThreadPool(10);
    private final Random random = new Random();

    public CompletableFuture<String> getValue() {
        return CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(2000);
                return "Selesai";
            } catch (InterruptedException e) {
                throw new CompletionException(e);
            }
        }, executorService);
    }

    @Test
    void testGetValue() throws ExecutionException, InterruptedException {
        Future<String> future = getValue();
        System.out.println("Menunggu hasil dari getValue()...");
        System.out.println(future.get());
    }

    private void execute(CompletableFuture<String> future, String value) {
        executorService.execute(() -> {
            try {
                Thread.sleep(1000 + random.nextInt(4000));
                System.out.println("Thread " + value + " selesai.");
                future.complete(value);
            } catch (InterruptedException e) {
                future.completeExceptionally(e);
            }
        });
    }

    public Future<String> getFastest() {
        CompletableFuture<String> future = new CompletableFuture<>();

        execute(future, "Thread 1");
        execute(future, "Thread 2");
        execute(future, "Thread 3");

        return future;
    }

    @Test
    void testGetFastest() throws ExecutionException, InterruptedException {
        System.out.println("Mencari thread tercepat...");
        Future<String> fastest = getFastest();
        System.out.println("Thread tercepat adalah: " + fastest.get());
    }

    @Test
    void testCompletableFutureStage() throws ExecutionException, InterruptedException {
        CompletableFuture<String> future = getValue();
        CompletableFuture<String[]> future2 = future
                .thenApply(String::toUpperCase)
                .thenApply(s -> s.split(""));

        String[] result = future2.get();
        for (var value : result) {
            System.out.println(value);
        }
    }
}
