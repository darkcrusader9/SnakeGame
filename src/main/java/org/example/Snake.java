package org.example;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashSet;
import java.util.Set;

public class Snake {
    public Deque<Position> snake;
    public Set<Position> body;

    public Snake(){
        this.body = new HashSet<>();
        this.snake = new ArrayDeque<>();

        Position initialPos = new Position(0, 0);
        snake.offerFirst(initialPos);
        body.add(initialPos);
    }

    public Position getHead(){
        return this.snake.peekFirst();
    }

    public Position removeHead(){
        return this.snake.pollFirst();
    }

    public void addHead(Position newPos){
        this.snake.offerFirst(newPos);
    }

    public Position getTail(){
        return this.snake.peekLast();
    }

    public Position removeTail(){
        return this.snake.pollLast();
    }

    public void addTail(Position newPos){
        this.snake.offerLast(newPos);
    }

    public boolean eatsItself(Position newPos){
        return body.contains(newPos);
    }

    public boolean move(Position newHead, boolean hasEatenFood){
        //remove tail
        Position tail = removeTail();
        body.remove(tail);

        //check if snake eats itself
        if(eatsItself(newHead))
            return false;

        //add new head to snake
        addHead(newHead);
        body.add(newHead);

        //check if food is present, if yes add tail back to simulate growth
        if(hasEatenFood){
            addTail(tail);
            body.add(tail);
        }
        return true;
    }

    public int getLength(){
        return this.snake.size();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Snake: ");
        for (Position p : snake) {
            sb.append(p).append(" ");
        }
        return sb.toString().trim();
    }
}
