package LLD.snakegame;

public class SnakeGameImpl implements SnakeGame{

    private GameBoard board;
    private Snake snake;
    private Position nextPosition;

    public SnakeGameImpl(GameBoard board, Snake snake) {
        this.board = board;
        this.snake = snake;
    }



    @Override
    public void makeMove(SnakeDirection direction) {

            this. nextPosition = getNextPosition(board,snake,direction);
            snake.makeMove(nextPosition);
    }

    private Position getNextPosition(GameBoard board, Snake snake, SnakeDirection direction) {

        Position position =  snake.getHead();
        switch (direction){
            case East:
                   Position next = new Position(position.x,  (position.y-1 + board.getColumns() )% board.getColumns());
                   return next;
        }
        return null;
    }

    @Override
    public boolean isGameOver() {
        return snake.isWithinBody(nextPosition);
    }
}
