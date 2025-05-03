package org.example;

import java.util.*;

public class SnakeGame {
    public int height, width;
    public int[][] food;
    public int foodIndex;
    Snake snake;
    Board board;
    private String currentDirection;

    public SnakeGame(int width, int height, int[][] food) {
        this.height = height;
        this.width = width;
        this.food = food;
        this.foodIndex = 0;
        this.snake = new Snake();
        this.board = new Board(height, width, food);
        this.currentDirection = "R";
    }

    public int tick(){
        return move(currentDirection);
    }

    public void changeDirection(String newDirection) {
        if (isValidDirectionChange(newDirection)) {
            this.currentDirection = newDirection;
        }
    }

    private boolean isValidDirectionChange(String newDir) {
        if (currentDirection.equals("U") && newDir.equals("D")) return false;
        if (currentDirection.equals("D") && newDir.equals("U")) return false;
        if (currentDirection.equals("L") && newDir.equals("R")) return false;
        if (currentDirection.equals("R") && newDir.equals("L")) return false;
        return true;
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

    public void printState() {
        char[][] grid = new char[height][width];
        for (int i = 0; i < height; i++) {
            Arrays.fill(grid[i], '.');
        }

        // Place food
        if (board.foodIndex < food.length) {
            int[] f = food[board.foodIndex];
            grid[f[0]][f[1]] = 'F';
        }

        // Place snake body
        for (Position p : snake.snake) {
            grid[p.row][p.col] = 'o';
        }

        // Mark snake head
        Position head = snake.getHead();
        if (head != null) {
            grid[head.row][head.col] = 'S';
        }

        // Print the grid
        System.out.println("Current Board:");
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                System.out.print(grid[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println("--------------");
    }


}
