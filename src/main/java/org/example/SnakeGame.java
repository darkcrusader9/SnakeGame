package org.example;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashSet;
import java.util.Set;

public class SnakeGame {
    public int height, width;
    public int[][] food;
    public int foodIndex;
    Snake snake;

    Board board;

    public SnakeGame(int width, int height, int[][] food) {
        this.height = height;
        this.width = width;
        this.food = food;
        this.foodIndex = 0;
        this.snake = new Snake();
        this.board = new Board(height, width, food);
    }

    public int move(String direction){
        Position currHead = snake.getHead();
        int newRow = currHead.row;
        int newCol = currHead.col;

        switch(direction){
            case "U": newRow--; break;
            case "D": newRow++; break;
            case "L": newCol--; break;
            case "R": newCol++; break;
        }

        Position newHead = new Position(newRow, newCol);

        // Check boundary condition
        if (!board.isValid(newHead)) return -1;

        boolean hasEatenFood = board.hasEatenFood(newHead);

        // Move snake
        boolean success = snake.move(newHead, hasEatenFood);
        if (!success) return -1;

        // Consume food if eaten
        if (hasEatenFood) {
            board.consumeFood();
        }

        return snake.getLength() - 1;
    }

}
