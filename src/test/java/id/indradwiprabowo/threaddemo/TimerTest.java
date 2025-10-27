package id.indradwiprabowo.threaddemo;

import org.junit.jupiter.api.Test;

import java.util.Timer;
import java.util.TimerTask;

public class TimerTest {

    @Test
    void delayJob() throws InterruptedException {
        var taks = new TimerTask(){
            @Override
            public void run() {
                System.out.println("Delay Job");
            }
        };

        var timer = new Timer();
        timer.schedule(taks, 2000);

        Thread.sleep(3000);
    }

    @Test
    void periodJob() throws InterruptedException {
        var taks = new TimerTask(){
            @Override
            public void run() {
                System.out.println("Period Job");
            }
        };

        var timer = new Timer();
        timer.schedule(taks, 2000, 2000);

        Thread.sleep(10000);
    }

}
