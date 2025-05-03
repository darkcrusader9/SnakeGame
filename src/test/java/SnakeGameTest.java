import org.example.Position;
import org.example.SnakeGame;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SnakeGameTest {
    @Test
    public void testFunctionality(){
        int[][] food = new int[][]{
            {1, 2},
            {0, 1}
        };
        SnakeGame snakeGame = new SnakeGame(3, 2, food);
        assertEquals(0, snakeGame.move("R"));
        assertEquals(0, snakeGame.move("D"));
        assertEquals(1, snakeGame.move("R"));
        assertEquals(1, snakeGame.move("U"));
        assertEquals(2, snakeGame.move("L"));
        assertEquals(-1, snakeGame.move("U"));
    }

}
