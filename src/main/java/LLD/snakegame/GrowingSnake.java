package LLD.snakegame;


import java.util.*;

public class GrowingSnake implements Snake{
    private   int length;
    private Deque<Position> snakeBody;

    private SnakeDirection snakeDirection;
    int moveCount = 0;

    public GrowingSnake(Position head,int length) {

        this.length = length;
        this.snakeBody = initSnakeBody(head,length);
    }

    private static ArrayDeque<Position> initSnakeBody(Position head, int length) {

        ArrayDeque<Position> positions = new ArrayDeque<>();
        positions.offerFirst(head);
        for(int i=1;i<=length;i++){
            positions.offerLast(new Position(head.x+i,head.y));
        }
        return positions;
    }


    @Override
    public boolean isWithinBody(LLD.snakegame.Position nextPosition) {
      Set<LLD.snakegame.Position> positions =  getPositions();
        return positions.contains(nextPosition);
    }

    /**
     * TODO
     * @return
     */
    private Set<LLD.snakegame.Position> getPositions() {
            return new HashSet<>(snakeBody);
    }

    @Override
    public void changeSize() {
        length = length+1;
        Position currentTail =  this.snakeBody.peekLast();
        Position nextCell = getNextPosition(currentTail,snakeDirection);
        this.snakeBody.offerLast(nextCell);
    }

    private Position getNextPosition(Position currentTail, SnakeDirection snakeDirection) {
        return null;
    }

    @Override
    public void makeMove(LLD.snakegame.Position nextPosition) {

        moveCount++;
        if(moveCount>0 && moveCount%3 == 0){
            changeSize();
        }
        this.snakeBody .offerFirst(nextPosition) ;
        this.snakeBody.pollLast();

    }

    @Override
    public Position getHead() {
        return snakeBody.peekFirst();
    }


}
