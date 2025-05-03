package org.example;

import java.util.concurrent.*;

public class Main {
    public static void main(String[] args) {
        int[][] food = new int[][]{
                {1, 2},
                {0, 1}
        };
        SnakeGame game = new SnakeGame(3, 2, food);
        ScheduledExecutorService executor = Executors.newScheduledThreadPool(1);

        Runnable gameTick = () -> {
            int result = game.tick();
            if (result == -1) {
                System.out.println("Game Over");
                executor.shutdown();
            } else {
                System.out.println("Current Score: " + result);
                game.printState();
            }
        };

        executor.scheduleAtFixedRate(gameTick, 0, 1, TimeUnit.SECONDS);

        // Simulate user inputs in another thread
        new Thread(() -> {
            try {
                Thread.sleep(2000); // after 2 seconds
                game.changeDirection("D"); // move down
                game.printState();
                Thread.sleep(2000); // after 4 seconds
                game.changeDirection("R"); // move right
                game.printState();
                Thread.sleep(2000); // after 6 seconds
                game.changeDirection("U"); // move up
                game.printState();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
    }
}