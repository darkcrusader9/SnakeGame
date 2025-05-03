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
}
