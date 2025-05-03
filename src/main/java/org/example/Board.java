package org.example;

public class Board {
    int height;
    int width;
    int[][] food;
    int foodIndex;

    public Board(int height, int width, int[][] food) {
        this.height = height;
        this.width = width;
        this.food = food;
        this.foodIndex = 0;
    }

    public boolean isValid(Position pos) {
        return pos.row >= 0 && pos.row < height && pos.col >= 0 && pos.col < width;
    }

    public boolean hasEatenFood(Position newPos){
        if(foodIndex < food.length && food[foodIndex][0] == newPos.row && food[foodIndex][1] == newPos.col){
            return true;
        }
        return false;
    }

    public void consumeFood(){
        foodIndex++;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Board: ").append(height).append("x").append(width).append("\n");
        sb.append("Food: ");
        for (int i = 0; i < food.length; i++) {
            if (i == foodIndex) sb.append("["); // Indicate current food
            sb.append("(").append(food[i][0]).append(",").append(food[i][1]).append(")");
            if (i == foodIndex) sb.append("]");
            if (i != food.length - 1) sb.append(", ");
        }
        return sb.toString();
    }
}
